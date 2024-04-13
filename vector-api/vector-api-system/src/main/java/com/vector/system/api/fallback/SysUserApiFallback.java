package com.vector.system.api.fallback;

import com.vector.common.core.result.R;
import com.vector.system.api.SysUserApi;
import com.vector.system.entity.SysMenu;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SysUserApiFallback implements FallbackFactory<SysUserApi> {
    @Override
    public SysUserApi create(Throwable cause) {
        return new SysUserApi() {
            @Override
            public R<SysUser> loadUserByUsername(String username) {
                log.error("loadUserByUsername:SysUser模块调用失败");
                return R.fail("SysUser模块调用失败");
            }

            @Override
            public R<List<SysRole>> getUserRoles(Long userId) {
                log.error("getUserRoles:SysUser模块调用失败");
                return R.fail("SysUser模块调用失败");
            }

            @Override
            public R<List<SysMenu>> getUserMenus(Long userId) {
                log.error("getUserMenus:SysUser模块调用失败");
                return R.fail("SysUser模块调用失败");
            }
        };
    }
}
