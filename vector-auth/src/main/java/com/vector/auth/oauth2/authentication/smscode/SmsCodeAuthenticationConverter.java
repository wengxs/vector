package com.vector.auth.oauth2.authentication.smscode;

import com.vector.auth.oauth2.OAuth2EndpointUtils;
import com.vector.auth.oauth2.authentication.CustomAuthorizationGrantType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 短信验证码授权模式参数解析器
 */
public class SmsCodeAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getFormParameters(request);

        // grant_type (REQUIRED)
        String grantType = parameters.getFirst(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomAuthorizationGrantType.SMS_CODE.getValue().equals(grantType)) {
            return null;
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        // mobile (REQUIRED)
        String mobile = parameters.getFirst("mobile");
        if (!StringUtils.hasText(mobile)) {
            throw new OAuth2AuthenticationException("手机号不能为空");
        }

        // code (REQUIRED)
        String code = parameters.getFirst(OAuth2ParameterNames.CODE);
        if (!StringUtils.hasText(code)) {
            throw new OAuth2AuthenticationException("验证码不能为空");
        }

        Map<String, Object> additionalParameters = OAuth2EndpointUtils
                .getParametersIfMatchesAuthorizationCodeGrantRequest(
                        request,
                        OAuth2ParameterNames.GRANT_TYPE,
                        OAuth2ParameterNames.CLIENT_ID
                );

        return new SmsCodeAuthenticationToken(mobile, code, clientPrincipal, additionalParameters);
    }
}
