package com.vector.base.api;

import com.vector.base.api.fallback.BaseProductApiFallback;
import com.vector.base.entity.BaseProduct;
import com.vector.core.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
@FeignClient(value = "base-service", fallbackFactory = BaseProductApiFallback.class)
public interface BaseProductApi {

    /**
     * 获取产品信息
     * @param id
     * @return
     */
    @GetMapping("/base/product/{id}")
    R<BaseProduct> get(@PathVariable("id") Integer id);

    /**
     * 更新产品信息
     * @param baseProduct
     * @return
     */
    @PostMapping("/base/product/update")
    R<?> update(@RequestBody BaseProduct baseProduct);
}
