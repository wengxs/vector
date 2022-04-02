package com.vector.wms.api.fallback;

import com.vector.wms.api.WmsStockApi;
import com.vector.wms.entity.WmsStock;
import com.vector.core.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
public class WmsStockApiFallback implements FallbackFactory<WmsStockApi> {

    @Override
    public WmsStockApi create(Throwable cause) {

        return new WmsStockApi() {
            @Override
            public R<WmsStock> get(Integer id) {
                return R.fail("Wms模块调用失败");
            }
        };
    }
}
