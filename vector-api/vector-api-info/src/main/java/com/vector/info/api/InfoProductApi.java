package com.vector.info.api;

import com.vector.info.api.fallback.InfoProductApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = "info-service", fallbackFactory = InfoProductApiFallback.class)
public interface InfoProductApi {
}
