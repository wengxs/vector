package com.vector.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.warehouse.entity.WarehouseProductStock;
import com.vector.warehouse.form.WarehouseProductStockLockForm;
import com.vector.warehouse.vo.WarehouseProductStockVo;

public interface WarehouseProductStockService extends IService<WarehouseProductStock> {

    WarehouseProductStockVo getVoById(Long id);

    IPage<WarehouseProductStockVo> pageVo(IPage<?> page, WarehouseProductStockVo query);

    /**
     * 锁定库存
     * @param lockForm
     */
    void lock(WarehouseProductStockLockForm lockForm);

    /**
     * 锁定库存
     * @param productId
     * @param qty
     */
    void lock(Long productId, Integer qty);

    /**
     * 释放库存
     * @param lockForm
     */
    void unlock(WarehouseProductStockLockForm lockForm);

    /**
     * 释放库存
     * @param productId
     * @param qty
     */
    void unlock(Long productId, Integer qty);
}
