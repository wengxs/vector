package com.vector.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.purchase.entity.PurchaseOrder;
import com.vector.purchase.form.PurchaseOrderForm;
import com.vector.purchase.vo.PurchaseOrderVo;

public interface PurchaseOrderService extends IService<PurchaseOrder> {

    PurchaseOrderVo getVoById(Long id);

    IPage<PurchaseOrderVo> pageVo(IPage<?> page, PurchaseOrderVo query);

    /**
     * 创建订单
     * @param purchaseOrderForm
     */
    void createOrder(PurchaseOrderForm purchaseOrderForm);

    /**
     * 修改订单
     * @param purchaseOrderForm
     */
    void updateById(PurchaseOrderForm purchaseOrderForm);

    /**
     * 取消订单
     * @param id
     */
    void cancel(Long id);

    /**
     * 签约采购
     * @param id
     */
    void sign(Long id);

    /**
     * 物流信息回传
     * @param id
     * @param logisticsName
     * @param logisticsNo
     */
    void callbackLogistics(Long id, String logisticsName, String logisticsNo);

    /**
     * 采购单完结
     * @param id
     */
    void finished(Long id);
}
