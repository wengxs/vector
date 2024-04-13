package com.vector.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.enums.ResultCode;
import com.vector.common.core.result.IResultCode;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.core.util.OrderNoGenerator;
import com.vector.common.mq.constant.RabbitMqConstant;
import com.vector.purchase.constant.PurchaseConstant;
import com.vector.purchase.entity.PurchaseOrder;
import com.vector.purchase.entity.PurchaseOrderDetail;
import com.vector.purchase.enums.PurchaseOrderStatus;
import com.vector.purchase.form.PurchaseOrderForm;
import com.vector.purchase.mapper.PurchaseOrderDetailMapper;
import com.vector.purchase.mapper.PurchaseOrderMapper;
import com.vector.purchase.service.PurchaseOrderService;
import com.vector.purchase.vo.PurchaseOrderVo;
import com.vector.warehouse.api.WarehouseApi;
import com.vector.warehouse.enums.BizType;
import com.vector.warehouse.form.WarehouseReceiveForm;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

    @Autowired
    private OrderNoGenerator orderNoGenerator;
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    private WarehouseApi warehouseApi;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public PurchaseOrderVo getVoById(Long id) {
        PurchaseOrderVo vo = baseMapper.selectVoById(id);
        if (vo != null)
            vo.setDetails(purchaseOrderDetailMapper.selectVoByOrderId(id));
        return vo;
    }

    @Override
    public IPage<PurchaseOrderVo> pageVo(IPage<?> page, PurchaseOrderVo query) {
        return baseMapper.selectVoPage(page, query);
    }

    private void handleDetail(List<PurchaseOrderForm.Detail> details, Long orderId) {
        for (PurchaseOrderForm.Detail detail : details) {
            PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(detail.getProductId());
            orderDetail.setPrice(detail.getPrice());
            orderDetail.setQty(detail.getQty());
            orderDetail.setAmount(detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())));
            purchaseOrderDetailMapper.insert(orderDetail);
        }
    }

    @Override
    @Transactional
    public void createOrder(PurchaseOrderForm purchaseOrderForm) {
        PurchaseOrder order = new PurchaseOrder();
        order.setOrderNo(orderNoGenerator.generate(PurchaseConstant.NUMBER_PREFIX_ORDER));
        order.setOrderStatus(PurchaseOrderStatus.DRAFT);
        order.setSupplierId(purchaseOrderForm.getSupplierId());
        order.setPurchaseRemake(purchaseOrderForm.getPurchaseRemake());
        BigDecimal amount = purchaseOrderForm.getDetails().stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setAmount(amount);
        baseMapper.insert(order);
        handleDetail(purchaseOrderForm.getDetails(), order.getId());
    }

    @Override
    @Transactional
    public void updateById(PurchaseOrderForm purchaseOrderForm) {
        PurchaseOrder order = baseMapper.selectById(purchaseOrderForm.getId());
        BizAssert.notNull(order, "采购单不存在");
        BizAssert.isTrue(PurchaseOrderStatus.DRAFT.equals(order.getOrderStatus()), "该状态的采购单不可以修改");
        order.setSupplierId(purchaseOrderForm.getSupplierId());
        order.setPurchaseRemake(purchaseOrderForm.getPurchaseRemake());
        BigDecimal amount = purchaseOrderForm.getDetails().stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setAmount(amount);
        baseMapper.updateById(order);
        // 清除明细
        purchaseOrderDetailMapper.delete(new LambdaQueryWrapper<>(PurchaseOrderDetail.class)
                .eq(PurchaseOrderDetail::getOrderId, order.getId()));
        handleDetail(purchaseOrderForm.getDetails(), order.getId());
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        PurchaseOrder order = baseMapper.selectById(id);
        BizAssert.notNull(order, "采购单不存在");
        BizAssert.contains(Arrays.asList(
                PurchaseOrderStatus.DRAFT,
                PurchaseOrderStatus.APPROVING,
                PurchaseOrderStatus.SIGNING
                ), order.getOrderStatus(), "该状态的采购单不可以取消");
        order.setOrderStatus(PurchaseOrderStatus.CANCELED);
        order.setCanceledTime(new Date());
        baseMapper.updateById(order);
        // TODO 取消审批任务
    }

    @Override
    @GlobalTransactional(name = "purchaseOrderSign")
    public void sign(Long id) {
        PurchaseOrder order = baseMapper.selectById(id);
        BizAssert.notNull(order, "采购单不存在");
        BizAssert.isTrue(PurchaseOrderStatus.SIGNING.equals(order.getOrderStatus()),
                String.format("只有%s状态可执行此操作", PurchaseOrderStatus.SIGNING.getDesc()));
        List<PurchaseOrderDetail> details = purchaseOrderDetailMapper.selectList(
                new LambdaQueryWrapper<>(PurchaseOrderDetail.class).eq(PurchaseOrderDetail::getOrderId, id));
        BizAssert.notEmpty(details, "订单无采购明细");
        // 构建收货表单
        WarehouseReceiveForm form = new WarehouseReceiveForm();
        form.setBizType(BizType.PURCHASE);
        form.setBizNo(order.getOrderNo());
        form.setDetails(details.stream().map(orderDetail -> {
            WarehouseReceiveForm.Detail detail = new WarehouseReceiveForm.Detail();
            detail.setProductId(orderDetail.getProductId());
            detail.setQty(orderDetail.getQty());
            return detail;
        }).toList());
        // 远程创建收货单
//        R<?> r = warehouseApi.receiveCreate(form);
//        Assert.isTrue(r.isOk(), r.getMessage());
        // 使用mq异步创建收货单
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, "receiveCreate", form);

        order.setOrderStatus(PurchaseOrderStatus.PURCHASING);
        order.setSignedTime(new Date());
        baseMapper.updateById(order);
    }

    @Override
    public void callbackLogistics(Long id, String logisticsName, String logisticsNo) {
        PurchaseOrder order = baseMapper.selectById(id);
        BizAssert.notNull(order, "采购单不存在");
        BizAssert.isTrue(PurchaseOrderStatus.PURCHASING.equals(order.getOrderStatus()),
                String.format("只有%s状态可执行此操作", PurchaseOrderStatus.PURCHASING.getDesc()));
        Map<String, Object> message = new HashMap<>();
        message.put("bizNo", order.getOrderNo());
        message.put("logisticsName", logisticsName);
        message.put("logisticsNo", logisticsNo);
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, "receiveLogistics", message);
    }

    @Override
    @Transactional
    public void finished(Long id) {
        PurchaseOrder order = baseMapper.selectById(id);
        BizAssert.notNull(order, "采购单不存在");
        order.setOrderStatus(PurchaseOrderStatus.FINISHED);
        order.setFinishedTime(new Date());
        baseMapper.updateById(order);
    }
}
