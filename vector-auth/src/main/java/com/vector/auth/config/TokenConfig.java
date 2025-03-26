package com.vector.auth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.vector.common.security.constant.SecurityConstant;
import com.vector.common.security.domain.LoginUser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Configuration
public class TokenConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 用于签名访问令牌
     */
    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource() {
        // 每次启动会生成新的密钥，需要持久化秘钥
        String jwkSetStr = redisTemplate.opsForValue().get(SecurityConstant.JWK_SET_KEY);
        if (StringUtils.hasText(jwkSetStr)) {
            JWKSet jwkSet = JWKSet.parse(jwkSetStr);
            return new ImmutableJWKSet<>(jwkSet);
        } else {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAKey rsaKey = new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
            JWKSet jwkSet = new JWKSet(rsaKey);
            // 将JWKSet存储在Redis中
            redisTemplate.opsForValue().set(SecurityConstant.JWK_SET_KEY, jwkSet.toString(Boolean.FALSE));
            return new ImmutableJWKSet<>(jwkSet);
        }
    }

    /**
     * 启动时生成的带有密钥的KeyPair实例，用于创建上面的JWKSource
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     * JwtDecoder的一个实例，用于解码已签名的访问令牌
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                // Customize headers/claims for access_token
                if (context.getPrincipal() instanceof UsernamePasswordAuthenticationToken authenticationToken) {
                    Optional.ofNullable(authenticationToken.getPrincipal()).ifPresent(principal -> {
                        if (principal instanceof LoginUser loginUser) {
                            context.getClaims().claims(claims -> {
                                claims.put("jti", UUID.randomUUID().toString()); // TODO 当前版本没有自动生成jti，需要手动设置
                                claims.put(SecurityConstant.TOKEN_INFO_USER_ID, loginUser.getUserId());
                                claims.put(SecurityConstant.TOKEN_INFO_USERNAME, loginUser.getUsername());
                                claims.put(SecurityConstant.TOKEN_INFO_DEPT_ID, loginUser.getDeptId());
                                claims.put(SecurityConstant.TOKEN_INFO_DATA_SCOPE, loginUser.getDataScope());
                                Set<String> authorities = AuthorityUtils.authorityListToSet(loginUser.getAuthorities());
                                claims.put(SecurityConstant.TOKEN_INFO_AUTHORITIES, authorities);
                            });
                        }
                    });
                }
            }
        };
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(jwtCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }
}
