package com.vector.app.pojo.query;

import com.vector.app.enums.AppUserStatus;
import com.vector.common.core.pagination.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息 查询参数
 * @author wengxs
 * @date 2025/03/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserQuery extends PageQuery {

    /** 手机号 */
    private String mobile;

    /** OpenId */
    private String openid;

    /** 用户名 */
    private String username;

    /** 用户状态 */
    private AppUserStatus userStatus;
}
