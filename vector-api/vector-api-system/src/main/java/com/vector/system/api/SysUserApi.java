package com.vector.system.api;

import com.vector.common.core.result.R;
import com.vector.system.api.fallback.SysUserApiFallback;
import com.vector.system.dto.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
@FeignClient(value = "vector-system", fallbackFactory = SysUserApiFallback.class)
public interface SysUserApi {

    @GetMapping("/remote/user/load/{username}")
    R<UserAuthInfo> loadUserByUsername(@PathVariable("username") String username);
}
