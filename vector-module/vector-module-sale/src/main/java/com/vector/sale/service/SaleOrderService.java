package com.vector.sale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.sale.entity.SaleOrder;
import com.vector.sale.form.SaleOrderForm;
import com.vector.sale.vo.SaleOrderVo;

public interface SaleOrderService extends IService<SaleOrder> {

    SaleOrderVo getVoById(Long id);

    IPage<SaleOrderVo> pageVo(IPage<?> page, SaleOrderVo query);

    /**
     * 创建订单
     * @param purchaseOrderForm
     */
    void createOrder(SaleOrderForm purchaseOrderForm);

//    /**
//     * 修改订单
//     * @param purchaseOrderForm
//     */
//    void updateById(SaleOrderForm purchaseOrderForm);

    /**
     * 取消订单
     * @param id
     */
    void cancel(Long id);

    /**
     * 订单支付
     * @param id
     */
    void pay(Long id);
}
