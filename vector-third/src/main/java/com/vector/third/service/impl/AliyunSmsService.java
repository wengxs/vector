package com.vector.third.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.vector.third.pojo.entity.SmsLog;
import com.vector.third.service.SmsService;
import com.vector.third.service.SmsLogService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * 阿里云短信服务
 */
@Service
@ConditionalOnProperty(value = "sms.type", havingValue = "aliyun")
@ConfigurationProperties(prefix = "sms.aliyun")
@RequiredArgsConstructor
@Data
@Slf4j
public class AliyunSmsService implements SmsService {

    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
    private String domain;
    private String signName;

    @Autowired
    private SmsLogService smsLogService;

    @Override
    public boolean send(String mobile, String templateCode, String templateParam) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);

        boolean result = false;
        String message = "";
        try {
            CommonResponse response = client.getCommonResponse(request);
            if (!response.getHttpResponse().isSuccess()) {
                message = response.getData();
                log.error("短信发送失败：{}", message);
            }
            JSONObject data = JSONObject.parseObject(response.getData());
            if (data.containsKey("Message")) {
                if ("OK".equalsIgnoreCase(data.getString("Message"))) {
                    result = true;
                } else {
                    message = data.getString("Message");
                    log.error("短信发送失败：{}", message);
                }
            }
        } catch (ClientException e) {
            message = e.getMessage();
            log.error("短信发送失败：{}", message, e);
        }
        // 记录日志
        SmsLog smsLog = new SmsLog();
        smsLog.setPlatform("aliyun");
        smsLog.setMobile(mobile);
        smsLog.setTemplateCode(templateCode);
        smsLog.setTemplateParam(templateParam);
        smsLog.setResult(result ? "成功" : "失败");
        smsLog.setException(message);
        smsLogService.save(smsLog);
        return result;
    }
}
