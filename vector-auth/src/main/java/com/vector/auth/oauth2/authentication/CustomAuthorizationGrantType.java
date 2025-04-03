package com.vector.auth.oauth2.authentication;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * 自定义授权模式
 */
public class CustomAuthorizationGrantType {

    /** 密码模式 */
    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

    /** 短信验证码模式 */
    public static final AuthorizationGrantType SMS_CODE = new AuthorizationGrantType("sms_code");
}
