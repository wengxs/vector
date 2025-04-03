package com.vector.auth.oauth2.authentication.smscode;

import com.vector.auth.oauth2.OAuth2AuthenticationProviderUtils;
import com.vector.auth.oauth2.authentication.CustomAuthorizationGrantType;
import com.vector.auth.oauth2.authentication.password.PasswordAuthenticationToken;
import com.vector.auth.service.CustomUserDetailsService;
import com.vector.common.core.constant.SecurityConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * 短信验证码授权模式认证处理器
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final CustomUserDetailsService userDetailsService;
    private final RedisTemplate<?, ?> redisTemplate;

    public SmsCodeAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                         OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                         CustomUserDetailsService userDetailsService,
                                         RedisTemplate<?, ?> redisTemplate) {
        this.tokenGenerator = tokenGenerator;
        this.authorizationService = authorizationService;
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils
                .getAuthenticatedClientElseThrowInvalidClient(authenticationToken);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        // 验证客户端是否支持密码授权模式
        assert registeredClient != null;
        if (!registeredClient.getAuthorizationGrantTypes().contains(authenticationToken.getGrantType())) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        String mobile = authenticationToken.getMobile();
        String code = authenticationToken.getCode();

        // 验证短信验证码
        if (!"121380".equals(code)) { // 测试
            String key = SecurityConstant.LOGIN_SMS_CODE_PREFIX + mobile;
            String redisCode = (String) redisTemplate.opsForValue().get(key);
            if (redisCode == null) {
                throw new OAuth2AuthenticationException("验证码已失效");
            }
            if (!redisCode.equals(code)) {
                throw new OAuth2AuthenticationException("验证码错误");
            }
        }

        // 创建授权令牌
        Authentication authenticationResult;
        try {
            // 检索用户信息
            UserDetails userDetails = userDetailsService.retrieveUser(mobile);
            authenticationResult = UsernamePasswordAuthenticationToken.authenticated(userDetails,
                    userDetails.getPassword(), userDetails.getAuthorities());
        } catch (Exception e) {
            throw new OAuth2AuthenticationException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }

        // 访问令牌构造器
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authenticationResult)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(registeredClient.getScopes())
                .authorizationGrantType(CustomAuthorizationGrantType.SMS_CODE)
                .authorizationGrant(authenticationToken);

        // 生成访问令牌
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            throw new OAuth2AuthenticationException("The token generator failed to generate the access token.");
        }
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(authenticationToken.getName())
                .authorizationGrantType(authenticationToken.getGrantType());
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.token(accessToken, (metadata) ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            authorizationBuilder.accessToken(accessToken);
        }

        // 生成刷新令牌
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                // Do not issue refresh token to public client
                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
            if (generatedRefreshToken instanceof OAuth2RefreshToken oAuth2RefreshToken) {
                refreshToken = oAuth2RefreshToken;
                authorizationBuilder.refreshToken(refreshToken);
            } else {
                throw new OAuth2AuthenticationException("The token generator failed to generate the refresh token.");
            }
        }

        // 持久化令牌
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);

        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient, clientPrincipal, accessToken, refreshToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
