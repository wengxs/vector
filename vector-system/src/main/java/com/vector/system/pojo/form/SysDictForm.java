package com.vector.system.pojo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典表单对象
 * @author wengxs
 */
@Schema(description = "字典表单对象")
@Data
public class SysDictForm {

    /** ID */
    @Schema(description = "字典ID")
    private Long id;

    /** 字典名称 */
    @Schema(description = "字典名称", required = true)
    private String dictName;

    /** 字典键 */
    @Schema(description = "字典键", required = true)
    private String dictKey;

    /** 字典状态 */
    @Schema(description = "字典状态")
    private String dictStatus;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;
} 