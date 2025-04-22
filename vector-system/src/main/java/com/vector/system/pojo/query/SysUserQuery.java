package com.vector.system.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import com.vector.system.enums.SysUserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询参数
 * @author wengxs
 * @date 2024/5/12
 */
@Schema(description = "用户分页查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserQuery extends PageQuery {

    /** 用户名 */
    @Schema(description="用户名")
    private String username;

    /** 手机号码 */
    @Schema(description="手机号码")
    private String mobile;

    /** 邮箱 */
    @Schema(description="邮箱")
    private String email;

    /** 用户状态 */
    @Schema(description="用户状态")
    private SysUserStatus userStatus;

    /** 角色ID */
    @Schema(description="角色ID")
    private Long roleId;

    /** 部门ID */
    @Schema(description="部门ID")
    private Long deptId;
}
