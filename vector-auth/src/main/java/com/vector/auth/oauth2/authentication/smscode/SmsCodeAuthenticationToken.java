package com.vector.auth.oauth2.authentication.smscode;

import com.vector.auth.oauth2.authentication.CustomAuthorizationGrantType;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * 短信验证码授权模式身份验证令牌
 */
@Getter
public class SmsCodeAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /** 手机号 */
    private final String mobile;

    /** 短信验证码 */
    private final String code;

    /** 授权模式 */
    private static final AuthorizationGrantType grantType = CustomAuthorizationGrantType.SMS_CODE;

    public SmsCodeAuthenticationToken(String mobile,
                                      String code,
                                      Authentication clientPrincipal,
                                      @Nullable Map<String, Object> additionalParameters) {
        super(grantType, clientPrincipal, additionalParameters);
        this.mobile = mobile;
        this.code = code;
    }
}
