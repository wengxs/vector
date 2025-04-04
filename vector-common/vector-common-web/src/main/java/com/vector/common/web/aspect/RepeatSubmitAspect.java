package com.vector.common.web.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.vector.common.core.util.BizAssert;
import com.vector.common.redis.util.RedisUtil;
import com.vector.common.security.util.SecurityUtils;
import com.vector.common.web.annotation.PreventRepeatSubmit;
import com.vector.common.web.util.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    @Autowired
    private RedisUtil redisUtil;
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();


    @Pointcut("@annotation(com.vector.common.web.annotation.PreventRepeatSubmit)")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ServletUtil.getRequest();
        Method method = getMethod(joinPoint);
        PreventRepeatSubmit annotation = method.getAnnotation(PreventRepeatSubmit.class);
        String uk = null;
        if (PreventRepeatSubmit.Type.TOKEN.equals(annotation.type())) {
            uk = SecurityUtils.getJti();
        }
        if (PreventRepeatSubmit.Type.PARAM.equals(annotation.type()) || StringUtils.isBlank(uk)) {
            // 如果jti为空，则取请求参数作为标识
            String[] params = parameterNameDiscoverer.getParameterNames(method);
            Object[] args = joinPoint.getArgs();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < (params != null ? params.length : 0); i++) {
                map.put(params[i], args[i]);
            }
            uk = JSONObject.toJSONString(map);
            log.debug("参数:{}", uk);
            uk = DigestUtils.md5DigestAsHex(uk.getBytes(StandardCharsets.UTF_8));
            log.debug("md5:{}", uk);
        }
        String key = String.format("Lock:RepeatSubmit:%s-%s-%s", request.getMethod(), request.getRequestURI(), uk);
        boolean isOk = redisUtil.setNX(key, 1, annotation.interval());
        BizAssert.isTrue(isOk, "您的请求已提交，请不要重复提交或等待片刻再尝试。");
    }

    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }
}
