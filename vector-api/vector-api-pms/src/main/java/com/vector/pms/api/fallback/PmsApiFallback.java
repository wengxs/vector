package com.vector.pms.api.fallback;

import com.vector.pms.api.PmsApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
public class PmsApiFallback implements FallbackFactory<PmsApi> {

    @Override
    public PmsApi create(Throwable cause) {

        return new PmsApi() {
        };
    }
}
