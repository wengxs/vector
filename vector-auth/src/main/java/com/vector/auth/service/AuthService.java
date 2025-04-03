package com.vector.auth.service;

import com.vector.common.core.constant.SecurityConstant;
import com.vector.common.core.result.R;
import com.vector.common.core.util.BizAssert;
import com.vector.common.redis.util.RedisUtil;
import com.vector.third.api.ThirdServiceApi;
import com.vector.third.dto.SmsSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ThirdServiceApi thirdServiceApi;
    @Value("${sms.aliyun.template-code.login}")
    private String loginSmsTemplateCode;

    public void sendLoginSmsCode(String mobile) {
        String code = String.format("%06d", new Random().nextInt(1000000));

        R<?> r = thirdServiceApi.sendSms(new SmsSendDto(mobile,
                loginSmsTemplateCode,
                "{\"code\":\"" + code + "\"}"));
        BizAssert.isTrue(r.isOk(), r.getMessage());
        redisUtil.set(SecurityConstant.LOGIN_SMS_CODE_PREFIX + mobile, code, 5 * 60);
    }
}
