package com.vector.pms.api;

import com.vector.pms.api.fallback.PmsApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author Zorg
 * @date 2022/3/25
 */
@Component
@FeignClient(value = "pms-service", fallbackFactory = PmsApiFallback.class)
public interface PmsApi {

}
