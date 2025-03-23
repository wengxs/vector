package com.vector.common.security.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.vector.common.core.constant.SecurityConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    private static JSONObject getTokenInfo() {
        String tokenInfo = getRequest().getHeader(SecurityConstant.TOKEN_INFO);
        return JSON.parseObject(tokenInfo);
    }

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

    public static Long getClientId() {
        return MapUtils.getLong(getTokenAttributes(), SecurityConstant.TOKEN_INFO_CLIENT_ID);
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
        return roles.contains(SecurityConstant.ROLE_KEY_ADMIN);
    }
}
