package com.vector.system.pojo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门表单对象
 * @author wengxs
 */
@Schema(description = "部门表单对象")
@Data
public class SysDeptForm {

    /** ID */
    @Schema(description = "部门ID")
    private Long id;

    /** 上级部门ID */
    @Schema(description = "上级部门ID")
    private Long parentId;

    /** 部门名称 */
    @Schema(description = "部门名称", required = true)
    private String deptName;

    /** 部门领导 */
    @Schema(description = "部门负责人")
    private String leader;

    /** 联系电话 */
    @Schema(description = "联系电话")
    private String phone;

    /** 邮箱 */
    @Schema(description = "邮箱")
    private String email;

    /** 部门状态 */
    @Schema(description = "部门状态")
    private String deptStatus;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 排序 */
    @Schema(description = "显示顺序")
    private Integer sort;
} 