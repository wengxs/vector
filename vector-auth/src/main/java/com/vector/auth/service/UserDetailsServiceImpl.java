package com.vector.auth.service;

import com.alibaba.fastjson2.JSON;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.security.constant.SecurityConstant;
import com.vector.common.security.domain.LoginUser;
import com.vector.system.api.SysUserApi;
import com.vector.system.entity.SysMenu;
import com.vector.system.entity.SysRole;
import com.vector.system.entity.SysUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by Zorg
 * 2020/6/2 20:48
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserApi sysUserApi;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
//        String principalName = request.getUserPrincipal().getName();
        log.info("request.getParameter(\"client_id\")={}", clientId);
//        log.info("principal.getName()={}", principalName);
        log.info("loadUserByUsername({})", username);
        R<SysUser> r = sysUserApi.loadUserByUsername(username);
        BizAssert.isTrue(r.isOk(), r.getMessage());
        SysUser user = r.getData();
        log.info("登录结果={}", JSON.toJSONString(r));
        if (user == null)
            throw new UsernameNotFoundException("用户不存在");
        Set<String> permissions = getPermissionsByUserId(user.getId());
        permissions.addAll(getRolesByUserId(user.getId()));
        LoginUser loginUser = new LoginUser();
        loginUser.setClientId(clientId);
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setEnabled(user.getEnabled());
        loginUser.setPermissions(permissions);

        log.info("登录结果={}", JSON.toJSONString(loginUser));
        return loginUser;
    }

    public Set<String> getRolesByUserId(Long userId) {
        Set<String> roleList = new HashSet<>();
        if (userId.equals(SecurityConstant.ADMIN_ID)) {
            roleList.add(SecurityConstant.ADMIN_ROLES);
            return roleList;
        }
        R<List<SysRole>> r = sysUserApi.getUserRoles(userId);
        log.info("getUserRoles.r={}", JSON.toJSONString(r));
        List<SysRole> roles = r.getData();
        for (SysRole role : roles) {
            roleList.add("ROLE_" + role.getRoleKey());
        }
        return roleList;
    }

    private Set<String> getPermissionsByUserId(Long userId) {
        if (userId.equals(SecurityConstant.ADMIN_ID)) {
            Set<String> permissions = new HashSet<>();
            permissions.add(SecurityConstant.ADMIN_PERMISSIONS);
            return permissions;
        }
        List<SysMenu> menus = sysUserApi.getUserMenus(userId).getData();
        return menus.stream()
                .map(SysMenu::getPermission)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
    }

}
