package com.vector.app.pojo.dto;

import com.vector.app.enums.AppUserStatus;
import lombok.Data;

/**
 * 用户信息 DTO
 * @author wengxs
 * @date 2025/03/31
 */
@Data
public class AppUserDTO {

    /** ID */
    private Long id;

    /** 手机号 */
    private String mobile;

    /** OpenId */
    private String openid;

    /** 用户名 */
    private String username;

    /** 用户状态 */
    private AppUserStatus userStatus;
}
