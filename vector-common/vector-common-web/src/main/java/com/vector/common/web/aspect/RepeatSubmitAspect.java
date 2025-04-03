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

import java.lang.reflect.Method;
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
        String uk = SecurityUtils.getJti();
        HttpServletRequest request = ServletUtil.getRequest();
        Method method = getMethod(joinPoint);
        PreventRepeatSubmit annotation = method.getAnnotation(PreventRepeatSubmit.class);
        if (StringUtils.isBlank(uk) && method.getParameterCount() > 0) {
            // 如果jti为空，则取请求参数作为标识
            String[] params = parameterNameDiscoverer.getParameterNames(method);
            Object[] args = joinPoint.getArgs();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < (params != null ? params.length : 0); i++) {
                map.put(params[i], args[i]);
            }
            uk = JSONObject.toJSONString(map);
        }
        log.debug(uk);
        String key = "Lock:RepeatSubmit:" + request.getMethod() + "-" + request.getRequestURI() + "-" + uk;
        boolean isOk = redisUtil.setNX(key, 1, annotation.interval());
        BizAssert.isTrue(isOk, "您的请求已提交，请不要重复提交或等待片刻再尝试。");
    }

    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }
}
