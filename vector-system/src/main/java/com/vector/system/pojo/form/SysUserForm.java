package com.vector.system.pojo.form;

import com.vector.system.enums.SysUserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author wengxs
 */
@Schema(description = "用户表单对象")
@Data
public class SysUserForm {

    /** 用户ID */
    @Schema(description="用户ID")
    private Long Id;

    /** 用户名 */
    @Schema(description="用户名")
    private String username;

    /** 昵称 */
    @Schema(description="昵称")
    private String nickname;

    /** 密码 */
    @Schema(description="密码")
    private String password;

    /** 手机号码 */
    @Schema(description="手机号码")
    private String mobile;

    /** 邮箱 */
    @Schema(description="邮箱")
    private String email;

    /** 性别 */
    @Schema(description="性别")
    private String gender;

    /** 用户头像 */
    @Schema(description="用户头像")
    private String avatar;

    /** 用户状态 */
    @Schema(description="用户状态")
    private SysUserStatus userStatus;

    /** 部门ID */
    @Schema(description="部门ID")
    private Long deptId;

    /** 备注 */
    @Schema(description="备注")
    private String remark;

    /** 角色ID集合 */
    @Schema(description="角色ID集合")
    private Long[] roleIds;
}
