package com.vector.system.api;

import com.vector.common.core.result.R;
import com.vector.system.api.fallback.SysUserApiFallback;
import com.vector.system.entity.SysMenu;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(value = "system-service", fallbackFactory = SysUserApiFallback.class)
public interface SysUserApi {

    @GetMapping("/remote/user/load/{username}")
    R<SysUser> loadUserByUsername(@PathVariable("username") String username);

    @GetMapping("/remote/user/roles/{userId}")
    R<List<SysRole>> getUserRoles(@PathVariable("userId") Long userId);

    @GetMapping("/remote/user/menus/{userId}")
    R<List<SysMenu>> getUserMenus(@PathVariable("userId") Long userId);
}
