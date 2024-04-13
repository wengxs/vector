//package com.vector.gateway.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vector.common.core.result.R;
//import com.vector.common.security.constant.SecurityConstant;
//import com.vector.common.security.domain.LoginUser;
//import com.vector.common.security.service.TokenService;
//import com.vector.gateway.util.WebUtils;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//public class AuthorizeFilter implements GlobalFilter, Ordered {
//
//    @Autowired
//    private TokenService tokenService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @SneakyThrows
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("AuthorizeFilter.filter()校验Token有效性");
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        String token = request.getHeaders().getFirst(SecurityConstant.TOKEN_HEADER);
//        if (StringUtils.isBlank(token)) {
//            log.info("token isBlank");
//            return chain.filter(exchange);
//        } else if (StringUtils.startsWith(token, SecurityConstant.BASIC_PREFIX)) {
//            // 登录放行
//            log.info("token is {}", token);
//            return chain.filter(exchange);
//        }
//        // 解析jwt进行校验
//        token = token.substring(SecurityConstant.TOKEN_PREFIX.length());
////        Claims claims = Jwts.parser()
////                .setSigningKey(SecurityConstant.TOKEN_SECRET)
////                .parseClaimsJws(jwtStr)
////                .getBody();
////        String token = claims.get(SecurityConstant.LOGIN_USER_KEY, String.class);
//
////        JWSObject jwt = JWSObject.parse(jwtStr);
////        Map<String, Object> payload = jwt.getPayload().toJSONObject();
////        String token = (String) payload.get(SecurityConstant.LOGIN_USER_KEY);
//        LoginUser loginUser = tokenService.getLoginUser(token);
//        if (loginUser == null) {
//            return WebUtils.writeErrorResponse(response,
//                    R.fail(HttpStatus.UNAUTHORIZED.value(), "令牌无效或已过期"));
//        }
//        tokenService.refreshToken(loginUser);
//        request = exchange.getRequest().mutate()
//                .header(SecurityConstant.TOKEN_INFO, objectMapper.writeValueAsString(loginUser))
//                .build();
//
//        exchange = exchange.mutate().request(request).build();
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
