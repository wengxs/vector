package com.vector.gateway.util;

import com.alibaba.fastjson2.JSON;
import com.vector.common.core.result.R;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class WebUtils {

    public static Mono<Void> writeErrorResponse(ServerHttpResponse response, R<?> r) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.getHeaders().setCacheControl("no-cache");
        String body = JSON.toJSONString(r);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer)).doOnError(error -> DataBufferUtils.release(buffer));
    }
}
