package com.vector.third.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信发送记录 查询参数
 * @author wengxs
 * @date 2025/04/02
 */
@Schema(description = "短信发送记录查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsLogQuery extends PageQuery {

    /** 短信平台 */
    @Schema(description = "短信平台")
    private String platform;

    /** 手机号码 */
    @Schema(description = "手机号码")
    private String mobile;

    /** 模版代码 */
    @Schema(description = "模版代码")
    private String templateCode;

    /** 发送结果 */
    @Schema(description = "发送结果")
    private String result;
}
