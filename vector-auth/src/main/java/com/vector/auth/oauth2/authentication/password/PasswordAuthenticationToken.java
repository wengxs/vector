package com.vector.auth.oauth2.authentication.password;

import com.vector.auth.oauth2.authentication.CustomAuthorizationGrantType;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * 用户名密码授权模式身份验证令牌
 */
@Getter
public class PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /** 用户名 */
    private final String username;
    /** 密码 */
    private final String password;

    private static final AuthorizationGrantType grantType = CustomAuthorizationGrantType.PASSWORD;

    /**
     * @param username               the username
     * @param password               the password
     * @param clientPrincipal        the authenticated client principal
     * @param additionalParameters   the additional parameters
     */
    public PasswordAuthenticationToken(String username,
                                       String password,
                                       Authentication clientPrincipal,
                                       @Nullable Map<String, Object> additionalParameters) {
        super(grantType, clientPrincipal, additionalParameters);
        this.username = username;
        this.password = password;
    }
}
