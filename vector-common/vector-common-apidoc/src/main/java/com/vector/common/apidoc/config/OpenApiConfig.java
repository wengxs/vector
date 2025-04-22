package com.vector.common.apidoc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * OpenAPI 配置类
 * @author wengxs
 */
@Configuration
public class OpenApiConfig {

    /** OAuth2 认证 endpoint */
    @Value("${spring.security.oauth2.authorizationserver.token-uri}")
    private String tokenUrl;

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                new SecurityScheme()
                                        // OAuth2 授权模式
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .name(HttpHeaders.AUTHORIZATION)
                                        .flows(new OAuthFlows()
                                                .password(
                                                        new OAuthFlow()
                                                                .tokenUrl(tokenUrl)
                                                                .refreshUrl(tokenUrl)
                                                )
                                        )
                                        // 安全模式使用Bearer令牌（即JWT）
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                        )
                )
                // 接口全局添加 Authorization 参数
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
    }
}
