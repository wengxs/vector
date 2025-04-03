package com.vector.app.api;

import com.vector.app.api.fallback.AppUserApiFallback;
import com.vector.app.dto.UserAuthInfo;
import com.vector.common.core.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "vector-app", fallbackFactory = AppUserApiFallback.class)
public interface AppUserApi {

    @GetMapping("/remote/user/load/{mobileOrOpenid}")
    R<UserAuthInfo> loadUser(@PathVariable("mobileOrOpenid") String mobileOrOpenid);
}
