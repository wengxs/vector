package com.vector.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.result.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ConfigurationProperties(prefix = "security")
@Slf4j
public class ResourceSecurityConfig {

    @Autowired
    private ObjectMapper objectMapper;
    @Setter
    private List<String> whitelistPaths;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        log.info("whitelist path:{}", objectMapper.writeValueAsString(whitelistPaths));
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                            "/error",
                            "/webjars/**",
                            "/doc.html",
                            "/swagger-resources/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll();
                    if (!CollectionUtils.isEmpty(whitelistPaths)) {
                        for (String whitelistPath : whitelistPaths) {
                            requests.requestMatchers(mvcMatcherBuilder.pattern(whitelistPath)).permitAll();
                        }
                    }
                    requests.anyRequest().authenticated();
                });
        http
                .oauth2ResourceServer(configurer -> configurer
                        .jwt(jwtConfigurer -> jwtAuthenticationConverter())
                        .authenticationEntryPoint((request, response, authException) -> {
                            log.error("未登录：{}", authException.getMessage(), authException);
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(objectMapper.writeValueAsString(
                                    R.fail(HttpStatus.UNAUTHORIZED.value(), "未登录")
                            ));
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.error("没有权限访问：{}", accessDeniedException.getMessage(), accessDeniedException);
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(objectMapper.writeValueAsString(
                                    R.fail(HttpStatus.FORBIDDEN.value(), "没有权限访问")
                            ));
                        })
                );
        return http.build();
    }

    /**
     * 自定义JWT Converter
     *
     * @return Converter
     * @see JwtAuthenticationProvider#setJwtAuthenticationConverter(Converter)
     */
    @Bean
    public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstant.TOKEN_INFO_AUTHORITIES);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
