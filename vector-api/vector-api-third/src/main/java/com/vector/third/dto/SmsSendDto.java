package com.vector.third.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsSendDto {

    /** 手机号码 */
    private String mobile;

    /** 短信模板 */
    private String templateCode;

    /** 模版参数 */
    private String templateParam;
}
