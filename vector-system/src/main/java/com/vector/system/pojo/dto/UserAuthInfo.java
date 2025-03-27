package com.vector.system.pojo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserAuthInfo {

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 密码 */
    private String password;

    /** 手机号码 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 性别 */
    private String gender;

    /** 用户头像 */
    private String avatar;

    /** 部门ID */
    private Long deptId;

    /** 数据权限 */
    private String dataScope;

    /** 帐号状态（0停用，1正常） */
    private Boolean enabled;

    /** 角色集合 */
    private Set<String> roles;

    /** 权限集合 */
    private Set<String> permissions;

}
