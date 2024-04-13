package com.vector.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.common.core.result.R;
import com.vector.common.security.filter.AuthenticationFilter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    private AuthenticationFilter authenticationFilter;
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
                    requests.requestMatchers("/error").permitAll();
                    if (!CollectionUtils.isEmpty(whitelistPaths)) {
                        for (String whitelistPath : whitelistPaths) {
                            requests.requestMatchers(mvcMatcherBuilder.pattern(whitelistPath)).permitAll();
                        }
                    }
                    requests.anyRequest().authenticated();
                })
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            log.info("未登录：{}", authException.getMessage());
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(objectMapper.writeValueAsString(
                                    R.fail(HttpStatus.UNAUTHORIZED.value(), "未登录")
                            ));
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.info("没有权限访问：{}", accessDeniedException.getMessage());
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(objectMapper.writeValueAsString(
                                    R.fail(HttpStatus.FORBIDDEN.value(), "没有权限访问")
                            ));
                        })
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
