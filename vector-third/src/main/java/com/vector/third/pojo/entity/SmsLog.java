package com.vector.third.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信发送记录 Entity
 * @author wengxs
 * @date 2025/04/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_log")
public class SmsLog extends BaseEntity<Long> {

    /** 短信平台 */
    private String platform;

    /** 手机号码 */
    private String mobile;

    /** 模版代码 */
    private String templateCode;

    /** 模版参数 */
    private String templateParam;

    /** 发送结果 */
    private String result;

    /** 异常信息 */
    private String exception;
}
