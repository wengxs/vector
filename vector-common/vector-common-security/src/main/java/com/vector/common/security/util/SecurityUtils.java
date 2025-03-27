package com.vector.common.security.util;

import com.vector.common.core.constant.SecurityConstant;
import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.*;
import java.util.stream.Collectors;

public class SecurityUtils {

    private static Map<String, Object> getTokenAttributes() {
        Map<String, Object> tokenAttributes = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            tokenAttributes = jwtAuthenticationToken.getTokenAttributes();
        }
        return tokenAttributes;
    }

    public static Long getUserId() {
        return MapUtils.getLong(getTokenAttributes(), SecurityConstant.TOKEN_INFO_USER_ID);
    }

    public static String getUsername() {
        return MapUtils.getString(getTokenAttributes(), SecurityConstant.TOKEN_INFO_USERNAME);
    }

    public static Long getDeptId() {
        return MapUtils.getLong(getTokenAttributes(), SecurityConstant.TOKEN_INFO_DEPT_ID);
    }

    public static String getDataScope() {
        return MapUtils.getString(getTokenAttributes(), SecurityConstant.TOKEN_INFO_DATA_SCOPE);
    }

    public static Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                    .stream()
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        }
        return roles;
    }

    public static boolean isAdmin() {
        Set<String> roles = getRoles();
        return roles.contains(SecurityConstant.ROLE_PREFIX + SecurityConstant.ROLE_KEY_ADMIN);
    }

    /**
     * 获取过期时间
     * @return
     */
    public static Long getExp() {
        return MapUtils.getLong(getTokenAttributes(), "exp");
    }

    /**
     * 获取过期时间
     * @return
     */
    public static String getJti() {
        return MapUtils.getString(getTokenAttributes(), "jti");
    }
}
