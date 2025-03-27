package com.vector.gateway.filter;

import com.nimbusds.jose.JWSObject;
import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.result.R;
import com.vector.gateway.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String BEARER_PREFIX = SecurityConstant.TOKEN_PREFIX;
    private static final String TOKEN_BLACKLIST_PREFIX = SecurityConstant.TOKEN_BLACKLIST_PREFIX;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("AuthorizeFilter.filter()校验Token有效性：{}", authorization);
        if (StringUtils.isBlank(authorization) || !StringUtils.startsWithIgnoreCase(authorization, BEARER_PREFIX)) {
            return chain.filter(exchange);
        }
        // 解析jwt进行校验
        String token = authorization.substring(BEARER_PREFIX.length());
        try {
            JWSObject jwt = JWSObject.parse(token);
            String jti = (String) jwt.getPayload().toJSONObject().get("jti");
            Boolean isBlackToken = redisTemplate.hasKey(TOKEN_BLACKLIST_PREFIX + jti);
            if (Boolean.TRUE.equals(isBlackToken)) {
                return WebUtils.writeErrorResponse(response,
                        R.fail(HttpStatus.UNAUTHORIZED.value(), "令牌无效或已过期"));
            }
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return WebUtils.writeErrorResponse(response,
                    R.fail(HttpStatus.UNAUTHORIZED.value(), "令牌无效或已过期"));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
