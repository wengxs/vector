package com.vector.wms.controller;

import com.alibaba.fastjson.JSON;
import com.vector.base.api.BaseProductApi;
import com.vector.base.entity.BaseProduct;
import com.vector.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@RestController
@RequestMapping("/wms/stock")
public class WmsStockController {

    @Autowired
    private BaseProductApi baseProductApi;

    @GetMapping("/product/{productId}")
    public R<?> getStock(@PathVariable Integer productId) {
        R<BaseProduct> r = baseProductApi.get(productId);
        System.out.println(JSON.toJSONString(r));
        Assert.isTrue(r.isOk(), r.getMassage());
        BaseProduct baseProduct = r.getData();
        return R.ok(baseProduct);
    }
}
