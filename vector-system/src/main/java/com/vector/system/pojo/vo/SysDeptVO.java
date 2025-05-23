package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 部门 VO
 * @author wengxs
 */
@Schema(description = "部门视图对象")
@Data
public class SysDeptVO {

    /** ID */
    @Schema(description = "部门ID")
    private Long id;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 上级部门ID */
    @Schema(description = "上级部门ID")
    private Long parentId;

    /** 祖级列表 */
    @Schema(description = "祖级列表")
    private String ancestors;

    /** 部门名称 */
    @Schema(description = "部门名称")
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

    /** 子部门 */
    @Schema(description = "子部门列表")
    private List<SysDeptVO> children;
}
