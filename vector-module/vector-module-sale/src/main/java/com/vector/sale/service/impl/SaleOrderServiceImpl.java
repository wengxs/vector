package com.vector.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.core.util.OrderNoGenerator;
import com.vector.sale.constant.SaleConstant;
import com.vector.sale.entity.SaleOrder;
import com.vector.sale.entity.SaleOrderDetail;
import com.vector.sale.enums.SaleOrderStatus;
import com.vector.sale.form.SaleOrderForm;
import com.vector.sale.mapper.SaleOrderDetailMapper;
import com.vector.sale.mapper.SaleOrderMapper;
import com.vector.sale.service.SaleOrderService;
import com.vector.sale.vo.SaleOrderVo;
import com.vector.warehouse.api.WarehouseApi;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {

    @Autowired
    private OrderNoGenerator orderNoGenerator;
    @Autowired
    private SaleOrderDetailMapper saleOrderDetailMapper;
    @Autowired
    private WarehouseApi warehouseApi;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Override
    public SaleOrderVo getVoById(Long id) {
        SaleOrderVo vo = baseMapper.selectVoById(id);
        if (vo != null)
            vo.setDetails(saleOrderDetailMapper.selectVoByOrderId(id));
        return vo;
    }

    @Override
    public IPage<SaleOrderVo> pageVo(IPage<?> page, SaleOrderVo query) {
        return baseMapper.selectVoPage(page, query);
    }

    /**
     * 参数拷贝
     * @param source
     * @param target
     */
    private void copy(SaleOrderForm source, SaleOrder target) {
        target.setPlatform(source.getPlatform());
        target.setShopName(source.getShopName());
        target.setBuyer(source.getBuyer());
        target.setMobile(source.getMobile());
        target.setAreaCode(source.getAreaCode());
        target.setArea(source.getArea());
        target.setAddress(source.getAddress());
        target.setOrderRemake(source.getOrderRemake());
    }

    /**
     * 处理订单明细
     * @param details
     * @param orderId
     */
    private void handleDetail(List<SaleOrderForm.Detail> details, Long orderId) {
        for (SaleOrderForm.Detail detail : details) {
            SaleOrderDetail orderDetail = new SaleOrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(detail.getProductId());
            orderDetail.setPrice(detail.getPrice());
            orderDetail.setQty(detail.getQty());
            orderDetail.setAmount(detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())));
            saleOrderDetailMapper.insert(orderDetail);
        }
    }

    @Override
    @GlobalTransactional
    public void createOrder(SaleOrderForm saleOrderForm) {
        String orderNo = orderNoGenerator.generate(SaleConstant.PREFIX_SALE_ORDER);
        // 锁定库存
        WarehouseProductStockLockForm lockForm = new WarehouseProductStockLockForm();
        lockForm.setOrderNo(orderNo);
        lockForm.setDetails(saleOrderForm.getDetails().stream().map(item -> {
            WarehouseProductStockLockForm.Detail detail = new WarehouseProductStockLockForm.Detail();
            detail.setProductId(item.getProductId());
            detail.setQty(item.getQty());
            return detail;
        }).toList());
        R<?> r = warehouseApi.productStockLock(lockForm);
        BizAssert.isTrue(r.isOk(), r.getMessage());
        // 生成订单
        SaleOrder order = new SaleOrder();
        order.setOrderNo(orderNo);
        order.setOrderStatus(SaleOrderStatus.UNPAID);
        copy(saleOrderForm, order);
        BigDecimal amount = saleOrderForm.getDetails().stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setAmount(amount);
        baseMapper.insert(order);
        handleDetail(saleOrderForm.getDetails(), order.getId());
    }

//    @Override
//    @Transactional
//    public void updateById(SaleOrderForm saleOrderForm) {
//        SaleOrder order = baseMapper.selectById(saleOrderForm.getId());
//        Assert.notNull(order, "销售单不存在");
//        Assert.isTrue(SaleOrderStatus.UNPAID.equals(order.getOrderStatus()), "该状态的销售单不可以修改");
//        copy(saleOrderForm, order);
//        BigDecimal amount = saleOrderForm.getDetails().stream()
//                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQty())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        order.setAmount(amount);
//        baseMapper.updateById(order);
//        // 清除明细
//        saleOrderDetailMapper.delete(new LambdaQueryWrapper<>(SaleOrderDetail.class)
//                .eq(SaleOrderDetail::getOrderId, order.getId()));
//        handleDetail(saleOrderForm.getDetails(), order.getId());
//    }

    @Override
    @Transactional
    public void cancel(Long id) {
        SaleOrder order = baseMapper.selectById(id);
        BizAssert.notNull(order, "销售单不存在");
        BizAssert.isTrue(SaleOrderStatus.UNPAID.equals(order.getOrderStatus()), "该状态的销售单不可以取消");
        order.setOrderStatus(SaleOrderStatus.CANCELED);
        order.setCancelTime(new Date());
        baseMapper.updateById(order);
        // 释放库存
        List<SaleOrderDetail> orderDetails = saleOrderDetailMapper.selectList(
                new LambdaQueryWrapper<>(SaleOrderDetail.class).eq(SaleOrderDetail::getOrderId, order.getId()));
        WarehouseProductStockLockForm lockForm = new WarehouseProductStockLockForm();
        lockForm.setOrderNo(order.getOrderNo());
        lockForm.setDetails(orderDetails.stream().map(item -> {
            WarehouseProductStockLockForm.Detail detail = new WarehouseProductStockLockForm.Detail();
            detail.setProductId(item.getProductId());
            detail.setQty(item.getQty());
            return detail;
        }).toList());
        R<?> r = warehouseApi.productStockUnlock(lockForm);
        BizAssert.isTrue(r.isOk(), r.getMessage());
    }

    @Override
    public void pay(Long id) {
        // 订单状态更新
        // 生成配送单
    }

}
