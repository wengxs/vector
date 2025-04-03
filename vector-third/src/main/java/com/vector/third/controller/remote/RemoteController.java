package com.vector.third.controller.remote;


import com.vector.common.core.result.R;
import com.vector.third.dto.SmsSendDto;
import com.vector.third.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信接口
 */
@RestController
@RequestMapping("/remote/third")
public class RemoteController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/sms/send")
    public R<?> smsSend(@RequestBody SmsSendDto body) {
        boolean isOk = smsService.send(body.getMobile(), body.getTemplateCode(), body.getTemplateParam());
        if (isOk) {
            return R.ok();
        } else {
            return R.fail("短信发送失败");
        }
    }
}
