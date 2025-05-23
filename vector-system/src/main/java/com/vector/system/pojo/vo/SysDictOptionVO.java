package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 字典选项 VO
 * @author wengxs
 */
@Schema(description = "字典选项视图对象")
@Data
public class SysDictOptionVO {

    /** ID */
    @Schema(description = "选项ID")
    private Long id;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 字典键 */
    @Schema(description = "字典键")
    private String dictKey;

    /** 标签 */
    @Schema(description = "选项标签")
    private String optionLabel;

    /** 项值 */
    @Schema(description = "选项值")
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
