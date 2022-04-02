package com.vector.base.api.fallback;

import com.vector.base.api.BaseProductApi;
import com.vector.base.entity.BaseProduct;
import com.vector.core.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
public class BaseProductApiFallback implements FallbackFactory<BaseProductApi> {

    @Override
    public BaseProductApi create(Throwable cause) {

        return new BaseProductApi() {
            @Override
            public R<BaseProduct> get(Integer id) {
                return R.fail("Base模块调用失败");
            }

            @Override
            public R<?> update(BaseProduct baseProduct) {
                return R.fail("Base模块调用失败");
            }
        };
    }
}
