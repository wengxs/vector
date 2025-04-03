package com.vector.third.api.fallback;

import com.vector.common.core.result.R;
import com.vector.third.api.ThirdServiceApi;
import com.vector.third.dto.SmsSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ThirdServiceApiFallback implements FallbackFactory<ThirdServiceApi> {
    @Override
    public ThirdServiceApi create(Throwable cause) {
        return new ThirdServiceApi() {
            @Override
            public R<?> sendSms(SmsSendDto body) {
                log.error("sendSms:third服务调用失败", cause);
                return R.fail("third服务调用失败");
            }
        };
    }
}
