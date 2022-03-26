package com.vector.base.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.vector.base.entity.BaseProduct;
import com.vector.base.service.BaseProductService;
import com.vector.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Zorg
 * @date 2022/3/24
 */
@RestController
@RequestMapping("/base/product")
@RefreshScope
public class BaseProductController {

    @Value("${test-data}")
    private String testData;
    @Autowired
    private BaseProductService baseProductService;

    @GetMapping("/test")
    public String test() {
        return testData;
    }

    @GetMapping("/{id}")
    @SentinelResource("getProductById")
    public R<?> get(@PathVariable Integer id) {
        BaseProduct product = baseProductService.getById(id);
        System.out.println(product.getCreateTime());
        System.out.println(JSON.toJSONString(product));
        return R.ok(product);
    }
}
