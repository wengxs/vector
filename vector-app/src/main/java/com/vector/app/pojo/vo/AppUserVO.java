package com.vector.app.pojo.vo;

import com.vector.app.enums.AppUserStatus;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息 VO
 * @author wengxs
 * @date 2025/03/31
 */
@Data
public class AppUserVO {

    /** ID */
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 手机号 */
    private String mobile;

    /** OpenId */
    private String openid;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 用户状态 */
    private AppUserStatus userStatus;

    /** 头像 */
    private String avatar;
}
