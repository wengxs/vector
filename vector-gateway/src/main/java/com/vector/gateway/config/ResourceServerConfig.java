//package com.vector.gateway.config;
//
//import com.vector.common.core.result.R;
//import com.vector.common.security.constant.SecurityConstant;
//import com.vector.gateway.util.WebUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Configuration
//@EnableWebFluxSecurity
//@Slf4j
//public class ResourceServerConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        log.info("ResourceServerConfig.securityWebFilterChain");
////        http.oauth2ResourceServer()
////                .jwt()
////                .jwtAuthenticationConverter(jwtAuthenticationConverter());
//////                .jwtDecoder(jwtDecoder());
////
////        http.oauth2ResourceServer()
////                .accessDeniedHandler(accessDeniedHandler())
////                .authenticationEntryPoint(authenticationEntryPoint());
////
////        http.authorizeExchange()
////                .pathMatchers("/auth/**", "/favicon.ico").permitAll() // TODO 白名单优化
////                .anyExchange().access(authorizationManager())
////                .and()
////                .exceptionHandling()
////                .accessDeniedHandler(accessDeniedHandler())
////                .authenticationEntryPoint(authenticationEntryPoint())
////                .and()
////                .csrf().disable();
//
//        http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> {
//                    exchange.anyExchange().permitAll();
//                });
//        return http.build();
//    }
//
//    /**
//     * 自定义受权管理器，判断用户是否有权限拜访
//     */
////    @Bean
////    public ReactiveAuthorizationManager<AuthorizationContext> authorizationManager() {
////        return (authentication, authorizationContext) -> {
////            log.info("check...........");
////            ServerWebExchange exchange = authorizationContext.getExchange();
////            ServerHttpRequest request = exchange.getRequest();
////            String requestMethod = request.getMethodValue();
////            String requestPath = request.getURI().getPath();
////            log.info("请求方式：{}，路径：{}", requestMethod, requestPath);
////            // 对应跨域的预检请求直接放行
////            if (request.getMethod() == HttpMethod.OPTIONS) {
////                return Mono.just(new AuthorizationDecision(true));
////            }
////            // TODO 拉取全部权限
////            return authentication
////                    .filter(Authentication::isAuthenticated)
////                    .flatMapIterable(Authentication::getAuthorities)
////                    .map(GrantedAuthority::getAuthority)
////                    .any(authority -> {
////                        log.info("请求用户的authority={}", authority);
////                        // TODO 进行权限校验
////                        return true;
////                    })
////                    .map(AuthorizationDecision::new)
////                    .defaultIfEmpty(new AuthorizationDecision(false));
////        };
////    }
////
////    /**
////     * token无效或者已过期自定义响应(匿名用户访问无权限资源时的异常）
////     */
////    @Bean
////    public ServerAuthenticationEntryPoint authenticationEntryPoint() {
////        return (exchange, ex) -> {
////            log.info("未登录:{}", ex.getMessage());
////            return Mono.defer(() -> Mono.just(exchange.getResponse()))
////                    .flatMap(response ->
////                            WebUtils.writeErrorResponse(response,
////                                    R.fail(HttpStatus.UNAUTHORIZED.value(), "token无效或者已过期")));
////        };
////    }
////
////    /**
////     * 访问被拒绝时处理（认证过的用户访问无权限资源时的异常）
////     */
////    @Bean
////    public ServerAccessDeniedHandler accessDeniedHandler() {
////        return (exchange, denied) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
////                .flatMap(response ->
////                        WebUtils.writeErrorResponse(response,
////                                R.fail(HttpStatus.UNAUTHORIZED.value(), "没有权限访问")));
////    }
////
////
////    /**
////     * @return
////     *ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
////     * 需要把jwt的Claim中的authorities加入
////     * 方案：重新定义权限管理器，默认转换器JwtGrantedAuthoritiesConverter
////     */
////    @Bean
////    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
////        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
////        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(SecurityConstant.AUTHORITY_PREFIX);
////        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstant.JWT_AUTHORITIES_KEY);
////        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
////        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
////    }
//
//}
