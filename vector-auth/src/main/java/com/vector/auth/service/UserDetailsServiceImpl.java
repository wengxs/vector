package com.vector.auth.service;

import com.alibaba.fastjson2.JSON;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.security.domain.LoginUser;
import com.vector.system.api.SysUserApi;
import com.vector.system.dto.UserAuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Zorg
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserApi sysUserApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserAuthInfo> r = sysUserApi.loadUserByUsername(username);
        BizAssert.isTrue(r.isOk(), r.getMessage());
        UserAuthInfo user = r.getData();
        log.info("登录结果={}", JSON.toJSONString(r));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<String> permissions = new HashSet<>();
        permissions.addAll(user.getRoles());
        permissions.addAll(user.getPermissions());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setDataScope(user.getDataScope());
        loginUser.setEnabled(user.getEnabled());
        loginUser.setPermissions(permissions);

        log.info("登录结果={}", JSON.toJSONString(loginUser));
        return loginUser;
    }
}
