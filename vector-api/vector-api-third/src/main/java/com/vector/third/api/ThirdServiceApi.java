package com.vector.third.api;

import com.vector.common.core.result.R;
import com.vector.third.api.fallback.ThirdServiceApiFallback;
import com.vector.third.dto.SmsSendDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "vector-third", fallbackFactory = ThirdServiceApiFallback.class)
public interface ThirdServiceApi {

    @PostMapping("/remote/third/sms/send")
    R<?> sendSms(@RequestBody SmsSendDto body);
}
