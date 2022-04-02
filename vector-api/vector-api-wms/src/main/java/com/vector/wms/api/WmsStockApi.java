package com.vector.wms.api;

import com.vector.wms.api.fallback.WmsStockApiFallback;
import com.vector.wms.entity.WmsStock;
import com.vector.core.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
@FeignClient(value = "wms-service", fallbackFactory = WmsStockApiFallback.class)
public interface WmsStockApi {

    /**
     * 获取产品信息
     * @param id
     * @return
     */
    @GetMapping("/wms/stock/{id}")
    R<WmsStock> get(@PathVariable("id") Integer id);
}
