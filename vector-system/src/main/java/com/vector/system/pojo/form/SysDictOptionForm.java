package com.vector.system.pojo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典选项表单对象
 * @author wengxs
 * @date 2025/03/01
 */
@Schema(description = "字典选项表单对象")
@Data
public class SysDictOptionForm {

    /** ID */
    @Schema(description = "选项ID")
    private Long id;

    /** 字典键 */
    @Schema(description = "字典键", required = true)
    private String dictKey;

    /** 标签 */
    @Schema(description = "选项标签", required = true)
    private String optionLabel;

    /** 项值 */
    @Schema(description = "选项值", required = true)
    private String optionValue;

    /** 排序 */
    @Schema(description = "显示顺序")
    private Integer sort;

    /** 选项状态 */
    @Schema(description = "选项状态")
    private String optionStatus;

    /** 样式 */
    @Schema(description = "选项样式")
    private String style;
} 