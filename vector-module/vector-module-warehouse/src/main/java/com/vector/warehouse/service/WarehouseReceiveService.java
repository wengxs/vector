package com.vector.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.warehouse.entity.WarehouseReceive;
import com.vector.warehouse.form.WarehouseCheckForm;
import com.vector.warehouse.form.WarehouseReceiveForm;
import com.vector.warehouse.vo.WarehouseReceiveVo;

public interface WarehouseReceiveService extends IService<WarehouseReceive> {

    WarehouseReceiveVo getVoById(Long id);

    IPage<WarehouseReceiveVo> pageVo(IPage<?> page, WarehouseReceiveVo query);

    /**
     * 生成收货单
     * @param warehouseReceiveForm
     */
    void create(WarehouseReceiveForm warehouseReceiveForm);

    /**
     * 收货单签收
     * @param id
     */
    void sign(Long id);

    /**
     * 收货验收
     * @param warehouseCheckForm
     */
    void check(WarehouseCheckForm warehouseCheckForm);
}
