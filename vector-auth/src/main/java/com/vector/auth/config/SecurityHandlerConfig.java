package com.vector.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.vector.common.core.result.R;
import com.vector.common.security.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Security认证处理器
 *
 * Created by Zorg
 * 2020/5/23 17:05
 */
@Configuration
@Slf4j
public class SecurityHandlerConfig {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录成功
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("登录成功");
            // TODO 保存用户登录信息
            if (authentication instanceof OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken) {
                Converter<OAuth2AccessTokenResponse, Map<String, Object>> converter =
                        new DefaultOAuth2AccessTokenResponseMapConverter();
                OAuth2AccessToken accessToken = accessTokenAuthenticationToken.getAccessToken();
                OAuth2RefreshToken refreshToken = accessTokenAuthenticationToken.getRefreshToken();
                Map<String, Object> additionalParameters = accessTokenAuthenticationToken.getAdditionalParameters();
                OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse
                        .withToken(accessToken.getTokenValue())
                        .tokenType(accessToken.getTokenType());
                if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
                    builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
                }
                if (refreshToken != null) {
                    builder.refreshToken(refreshToken.getTokenValue());
                }
                if (additionalParameters != null && !additionalParameters.isEmpty()) {
                    builder.additionalParameters(additionalParameters);
                }
                OAuth2AccessTokenResponse accessTokenResponse = builder.build();

                Map<String, Object> accessTokenResponseMap = converter.convert(accessTokenResponse);
                response.setContentType(ContentType.APPLICATION_JSON.toString());
                response.getWriter().write(objectMapper.writeValueAsString(R.ok(accessTokenResponseMap)));
            }
        };
    }

    /**
     * 登录失败
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            log.info("登录失败");
//                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(ContentType.APPLICATION_JSON.toString());
            if (exception instanceof OAuth2AuthenticationException authenticationException) {
                OAuth2Error error = authenticationException.getError();
                log.error("认证失败：{}", error.getErrorCode());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail(error.getErrorCode())));
            } else if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
                log.error("用户不存在：{}", exception.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("用户名或密码有误")));
            } else if (exception instanceof LockedException) {
                log.error("账号已锁定：{}", exception.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已锁定")));
            } else if (exception instanceof CredentialsExpiredException) {
                log.error("证书过期：{}", exception.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("证书过期")));
            } else if (exception instanceof AccountExpiredException) {
                log.error("账号已过期：{}", exception.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已过期")));
            } else if (exception instanceof DisabledException) {
                log.error("账号已禁用：{}", exception.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已禁用")));
            } else {
                log.error("登录失败：{}", exception.getMessage(), exception);
                response.getWriter().write(objectMapper.writeValueAsString(R.fail("登录失败")));
            }
        };
    }

    /**
     * 退出登录
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            String authorization = request.getHeader(SecurityConstant.TOKEN_HEADER);
            if (StringUtils.isNotBlank(authorization)
                    && StringUtils.startsWithIgnoreCase(authorization, SecurityConstant.TOKEN_PREFIX)) {
                String token = authorization.substring(SecurityConstant.TOKEN_PREFIX.length());
                try {
                    JWSObject jwt = JWSObject.parse(token);
                    Map<String, Object> payload = jwt.getPayload().toJSONObject();
                    String jti = (String) payload.get("jti");
                    Long expireTime = (Long) payload.get("exp");
                    if (expireTime == null) {
                        // token 永不过期则永久加入黑名单
                        redisTemplate.opsForValue().set(SecurityConstant.TOKEN_BLACKLIST_PREFIX + jti, "");
                    } else {
                        long currentTime = System.currentTimeMillis() / 1000;
                        if (currentTime < expireTime) {
                            long remainingTime = expireTime - currentTime;
                            redisTemplate.opsForValue().set(SecurityConstant.TOKEN_BLACKLIST_PREFIX + jti, "",
                                    remainingTime, TimeUnit.SECONDS);
                        }
                    }
                } catch (ParseException e) {
                    log.error("退出登录出错：{}", e.getMessage());
                }
            }
            log.info("用户退出登录成功");
            SecurityContextHolder.clearContext();
            response.setContentType(ContentType.APPLICATION_JSON.toString());
            response.getWriter().write(objectMapper.writeValueAsString(R.ok()));
        };
    }
}
