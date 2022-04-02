package com.vector.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.base.api.BaseProductApi;
import com.vector.base.entity.BaseProduct;
import com.vector.core.R;
import com.vector.wms.entity.WmsStock;
import com.vector.wms.mapper.WmsStockMapper;
import com.vector.wms.service.WmsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Zorg
 * @date 2022/3/28
 */
@Service
public class WmsStockServiceImpl extends ServiceImpl<WmsStockMapper, WmsStock> implements WmsStockService {

    @Autowired
    private BaseProductApi baseProductApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WmsStock wmsStock) {
        R<BaseProduct> r = baseProductApi.get(wmsStock.getProductId());
        Assert.isTrue(r.isOk(), r.getMassage());
        BaseProduct baseProduct = r.getData();
        baseProduct.setStock(wmsStock.getUsableQty());
        baseProductApi.update(baseProduct);
        int row = baseMapper.updateById(wmsStock);
        Assert.isTrue(row > 0, "库存不存在");
    }
}
