package com.vector.app.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.app.enums.AppUserStatus;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息 Entity
 * @author wengxs
 * @date 2025/03/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_user")
public class AppUser extends BaseEntity<Long> {

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
