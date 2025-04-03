package com.vector.third.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信发送记录 查询参数
 * @author wengxs
 * @date 2025/04/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ThirdSmsLogQuery extends PageQuery {

    /** 短信平台 */
    private String platform;

    /** 手机号码 */
    private String mobile;

    /** 模版代码 */
    private String templateCode;

    /** 发送结果 */
    private String result;
}
