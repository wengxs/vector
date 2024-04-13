package com.vector.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Zorg
 * 2020/5/23 17:44
 */
public class LoginUser implements UserDetails {

    /** 客户端ID */
    private String clientId;

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 帐号状态（0停用，1正常） */
    private Boolean enabled;

    private String token;

    private Long loginTime;

    private Long expireTime;

    private Set<String> permissions;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (permissions == null) {
            return null;
        }
        return permissions.stream()
                .filter(StringUtils::isNotBlank)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        // do nothing
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

//    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
