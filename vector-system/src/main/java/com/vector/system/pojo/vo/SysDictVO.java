package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 字典 VO
 * @author wengxs
 */
@Schema(description = "字典视图对象")
@Data
public class SysDictVO {

    /** ID */
    @Schema(description = "字典ID")
    private Long id;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 字典名称 */
    @Schema(description = "字典名称")
    private String dictName;

    /** 字典键 */
    @Schema(description = "字典键")
    private String dictKey;

    /** 字典状态 */
    @Schema(description = "字典状态")
    private String dictStatus;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;
}
