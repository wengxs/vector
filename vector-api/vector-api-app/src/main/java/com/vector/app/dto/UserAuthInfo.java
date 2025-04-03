package com.vector.app.dto;

import lombok.Data;

@Data
public class UserAuthInfo {

    /** 用户ID */
    private Long userId;

    /** 手机号码 */
    private String mobile;

    /** OpenId */
    private String openid;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 用户头像 */
    private String avatar;

    /** 帐号状态（0停用，1正常） */
    private Boolean enabled;
}
