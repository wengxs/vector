package com.vector.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.common.core.util.BizAssert;
import com.vector.common.core.util.OrderNoGenerator;
import com.vector.common.mq.constant.RabbitMqConstant;
import com.vector.warehouse.constant.WarehouseConstant;
import com.vector.warehouse.entity.*;
import com.vector.warehouse.enums.BizType;
import com.vector.warehouse.enums.WarehouseReceiveStatus;
import com.vector.warehouse.form.WarehouseCheckForm;
import com.vector.warehouse.form.WarehouseReceiveForm;
import com.vector.warehouse.mapper.*;
import com.vector.warehouse.service.WarehouseReceiveService;
import com.vector.warehouse.vo.WarehouseReceiveVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WarehouseReceiveServiceImpl extends ServiceImpl<WarehouseReceiveMapper, WarehouseReceive> 
        implements WarehouseReceiveService {

    @Autowired
    private OrderNoGenerator orderNoGenerator;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private WarehouseReceiveDetailMapper warehouseReceiveDetailMapper;
    @Autowired
    private WarehouseProductLotMapper warehouseProductLotMapper;
    @Autowired
    private WarehouseProductStockMapper warehouseProductStockMapper;
    @Autowired
    private WarehouseProductBatchMapper warehouseProductBatchMapper;

    @Override
    public WarehouseReceiveVo getVoById(Long id) {
        WarehouseReceiveVo vo = baseMapper.selectVoById(id);
        if (vo != null)
            vo.setDetails(warehouseReceiveDetailMapper.selectVoByReceiveId(id));
        return vo;
    }

    @Override
    public IPage<WarehouseReceiveVo> pageVo(IPage<?> page, WarehouseReceiveVo query) {
        return baseMapper.selectVoPage(page, query);
    }

    @Override
    @Transactional
    public void create(WarehouseReceiveForm warehouseReceiveForm) {
        WarehouseReceive receive = new WarehouseReceive();
        receive.setReceiveNo(orderNoGenerator.generate(WarehouseConstant.NUMBER_PREFIX_RECEIVE));
        receive.setReceiveStatus(WarehouseReceiveStatus.UNSENT);
        receive.setBizType(warehouseReceiveForm.getBizType());
        receive.setBizNo(warehouseReceiveForm.getBizNo());
        baseMapper.insert(receive);
        for (WarehouseReceiveForm.Detail detail : warehouseReceiveForm.getDetails()) {
            WarehouseReceiveDetail receiveDetail = new WarehouseReceiveDetail();
            receiveDetail.setReceiveId(receive.getId());
            receiveDetail.setProductId(detail.getProductId());
            receiveDetail.setQty(detail.getQty());
            warehouseReceiveDetailMapper.insert(receiveDetail);
        }
    }

    @Override
    @Transactional
    public void sign(Long id) {
        WarehouseReceive receive = baseMapper.selectById(id);
        BizAssert.notNull(receive, "收货单不存在");
        BizAssert.contains(Arrays.asList(
                WarehouseReceiveStatus.UNSENT,
                WarehouseReceiveStatus.SENT_OUT
        ), receive.getReceiveStatus(), String.format("该收货单状态%s", receive.getReceiveStatus().getDesc()));
        receive.setReceiveStatus(WarehouseReceiveStatus.SIGNED);
        receive.setSignedTime(new Date());
        baseMapper.updateById(receive);
        if (BizType.PURCHASE.equals(receive.getBizType())) {
            // 采购单完结
            Map<String, Object> message = new HashMap<>();
            message.put("bizNo", receive.getBizNo());
            rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, "purchaseFinished", message);
        }
    }

    @Override
    @Transactional
    public void check(WarehouseCheckForm warehouseCheckForm) {
        WarehouseReceive receive = baseMapper.selectById(warehouseCheckForm.getId());
        BizAssert.notNull(receive, "收货单不存在");
        BizAssert.isTrue(WarehouseReceiveStatus.SIGNED.equals(receive.getReceiveStatus()),
                String.format("该收货单状态%s", receive.getReceiveStatus().getDesc()));
        String batchNo = orderNoGenerator.generate(WarehouseConstant.NUMBER_PREFIX_PRODUCT_BATCH);
        receive.setReceiveStatus(WarehouseReceiveStatus.FINISHED);
        receive.setBatchNo(batchNo);
        baseMapper.updateById(receive);
        List<WarehouseReceiveDetail> receiveDetails = warehouseReceiveDetailMapper.selectList(
                new LambdaQueryWrapper<>(WarehouseReceiveDetail.class)
                        .eq(WarehouseReceiveDetail::getReceiveId, receive.getId())
        );
        // 验收入库
        for (WarehouseCheckForm.Detail detail : warehouseCheckForm.getDetails()) {
            Long productId = detail.getProductId();
            // 增加批号库存
            WarehouseProductLot productLot = warehouseProductLotMapper.selectOne(
                    new LambdaQueryWrapper<>(WarehouseProductLot.class)
                            .eq(WarehouseProductLot::getProductId, productId)
                            .eq(WarehouseProductLot::getLotNo, detail.getLotNo())
            );
            boolean isNew = productLot == null;
            if (isNew) {
                productLot = new WarehouseProductLot();
                productLot.setProductId(productId);
                productLot.setLotNo(detail.getLotNo());
                productLot.setProdDate(detail.getProdDate());
                productLot.setExpiredDate(detail.getExpiredDate());
                productLot.setTotalQty(0);
                productLot.setQualifiedQty(0);
                productLot.setUnqualifiedQty(0);
            }
            productLot.setQualifiedQty(productLot.getQualifiedQty() + detail.getQualifiedQty());
            productLot.setUnqualifiedQty(productLot.getUnqualifiedQty() + detail.getUnqualifiedQty());
            productLot.setTotalQty(productLot.getTotalQty() + detail.getQualifiedQty() + detail.getUnqualifiedQty());
            if (isNew) {
                warehouseProductLotMapper.insert(productLot);
            } else {
                warehouseProductLotMapper.updateById(productLot);
            }
            // 增加库存
            WarehouseProductStock productStock = warehouseProductStockMapper.selectOne(
                    new LambdaQueryWrapper<>(WarehouseProductStock.class)
                            .eq(WarehouseProductStock::getProductId, productId)
            );
            isNew = productStock == null;
            if (isNew) {
                productStock = new WarehouseProductStock();
                productStock.setProductId(productId);
                productStock.setTotalStock(0);
                productStock.setUsableStock(0);
                productStock.setLockedStock(0);
            }
            productStock.setUsableStock(productStock.getUsableStock() + detail.getQualifiedQty());
            productStock.setTotalStock(productStock.getTotalStock() + detail.getQualifiedQty());
            if (isNew) {
                warehouseProductStockMapper.insert(productStock);
            } else {
                warehouseProductStockMapper.updateById(productStock);
            }
            // 保存批次
            if (detail.getQualifiedQty() > 0) {
                WarehouseProductBatch productBatch = new WarehouseProductBatch();
                productBatch.setProductLotId(productLot.getId());
                productBatch.setBatchNo(batchNo);
                productBatch.setQualified(1);
                productBatch.setReceivedQty(detail.getQualifiedQty());
                productBatch.setUsableQty(detail.getQualifiedQty());
                productBatch.setReceiveId(receive.getId());
                WarehouseReceiveDetail receiveDetail = receiveDetails.stream()
                        .filter(item -> item.getProductId().equals(productId))
                        .findFirst().orElseThrow();
                productBatch.setReceiveDetailId(receiveDetail.getId());
                warehouseProductBatchMapper.insert(productBatch);
            }
            if (detail.getUnqualifiedQty() > 0) {
                WarehouseProductBatch productBatch = new WarehouseProductBatch();
                productBatch.setProductLotId(productLot.getId());
                productBatch.setBatchNo(batchNo);
                productBatch.setQualified(0);
                productBatch.setReceivedQty(detail.getUnqualifiedQty());
                productBatch.setUsableQty(detail.getUnqualifiedQty());
                productBatch.setReceiveId(receive.getId());
                WarehouseReceiveDetail receiveDetail = receiveDetails.stream()
                        .filter(item -> item.getProductId().equals(productId))
                        .findFirst().orElseThrow();
                productBatch.setReceiveDetailId(receiveDetail.getId());
                warehouseProductBatchMapper.insert(productBatch);
            }
        }
        // 保存收货单明细
        for (WarehouseReceiveDetail receiveDetail : receiveDetails) {
            int qualifiedQty = warehouseCheckForm.getDetails().stream()
                    .filter(item -> item.getProductId().equals(receiveDetail.getProductId()))
                    .mapToInt(WarehouseCheckForm.Detail::getQualifiedQty).sum();
            int unqualifiedQty = warehouseCheckForm.getDetails().stream()
                    .filter(item -> item.getProductId().equals(receiveDetail.getProductId()))
                    .mapToInt(WarehouseCheckForm.Detail::getUnqualifiedQty).sum();
            receiveDetail.setReceivedQty(qualifiedQty + unqualifiedQty);
            receiveDetail.setQualifiedQty(qualifiedQty);
            receiveDetail.setUnqualifiedQty(unqualifiedQty);
            warehouseReceiveDetailMapper.updateById(receiveDetail);
        }
    }
}
