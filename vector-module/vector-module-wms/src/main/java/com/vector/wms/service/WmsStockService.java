package com.vector.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.wms.entity.WmsStock;

/**
 * @author Zorg
 * @date 2022/3/28
 */
public interface WmsStockService extends IService<WmsStock> {

    void update(WmsStock wmsStock);
}
