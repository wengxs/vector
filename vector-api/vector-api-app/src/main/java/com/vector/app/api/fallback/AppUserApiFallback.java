package com.vector.app.api.fallback;

import com.vector.app.api.AppUserApi;
import com.vector.app.dto.UserAuthInfo;
import com.vector.common.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppUserApiFallback implements FallbackFactory<AppUserApi> {
    @Override
    public AppUserApi create(Throwable cause) {
        return new AppUserApi() {
            @Override
            public R<UserAuthInfo> loadUser(String mobileOrOpenid) {
                log.error("loadUserByMobile:AppUser模块调用失败", cause);
                return R.fail("AppUser模块调用失败");
            }
        };
    }
}
