package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 部门 Tree
 * @author wengxs
 */
@Schema(description = "部门树对象")
@Data
public class SysDeptTree {

    /** ID */
    @Schema(description = "部门ID")
    private Long id;

    /** 部门名称 */
    @Schema(description = "部门名称")
    private String deptName;

    /** 子部门 */
    @Schema(description = "子部门树")
    private List<SysDeptTree> children;
}
