package com.vector.third.service;

/**
 * 短信服务接口
 */
public interface SmsService {

    /**
     * 发送短信
     * @param mobile        手机号
     * @param templateCode  短信模板
     * @param templateParam 模版参数
     * @return 是否发送成功
     */
    boolean send(String mobile, String templateCode, String templateParam);
}
