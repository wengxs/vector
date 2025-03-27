package com.vector.system.api.fallback;

import com.vector.common.core.result.R;
import com.vector.system.api.SysUserApi;
import com.vector.system.dto.UserAuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysUserApiFallback implements FallbackFactory<SysUserApi> {
    @Override
    public SysUserApi create(Throwable cause) {
        return new SysUserApi() {
            @Override
            public R<UserAuthInfo> loadUserByUsername(String username) {
                log.error("loadUserByUsername:SysUser模块调用失败", cause);
                return R.fail("SysUser模块调用失败");
            }
        };
    }
}
