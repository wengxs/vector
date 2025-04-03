package com.vector.auth.service;

import com.alibaba.fastjson2.JSON;
import com.vector.app.api.AppUserApi;
import com.vector.app.dto.UserAuthInfo;
import com.vector.auth.model.LoginUser;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private AppUserApi appUserApi;

    @Override
    public UserDetails retrieveUser(String keyword) throws RuntimeException {
        R<UserAuthInfo> r = appUserApi.loadUser(keyword);
        BizAssert.isTrue(r.isOk(), r.getMessage());
        UserAuthInfo user = r.getData();
        log.debug("登录结果={}", JSON.toJSONString(r));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setEnabled(user.getEnabled());

        log.debug("登录结果={}", JSON.toJSONString(loginUser));
        return loginUser;
    }
}
