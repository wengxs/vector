package com.vector.system.pojo.vo;

import com.vector.system.enums.SysUserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户视图
 * @author wengxs
 */
@Schema(description ="用户视图对象")
@Data
public class SysUserVO {

    /** 用户ID */
    @Schema(description ="用户ID")
    private Long id;

    /** 创建时间 */
    @Schema(description ="创建时间")
    private Date createTime;

    /** 创建人 */
    @Schema(description ="创建人")
    private Long createBy;

    /** 用户名 */
    @Schema(description ="用户名")
    private String username;

    /** 昵称 */
    @Schema(description ="昵称")
    private String nickname;

    /** 手机号码 */
    @Schema(description ="手机号码")
    private String mobile;

    /** 邮箱 */
    @Schema(description ="邮箱")
    private String email;

    /** 性别 */
    @Schema(description ="性别")
    private String gender;

    /** 用户头像 */
    @Schema(description ="用户头像")
    private String avatar;

    /** 用户状态 */
    @Schema(description ="用户状态")
    private SysUserStatus userStatus;

    /** 部门ID */
    @Schema(description ="部门ID")
    private Long deptId;

    /** 备注 */
    @Schema(description ="备注")
    private String remark;

    /** 部门名称 */
    @Schema(description ="部门名称")
    private String deptName;

    /** 角色名称 */
    @Schema(description ="角色名称")
    private String roleNames;

    /** 角色ID集合 */
    @Schema(description ="角色ID集合")
    List<Long> roleIds = new ArrayList<>();
}
