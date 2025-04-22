package com.vector.system.pojo.form;

import com.vector.common.core.enums.DataScope;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author wengxs
 */
@Schema(description = "角色表单对象")
@Data
public class SysRoleForm {

    /** 角色ID */
    @Schema(description = "角色ID")
    private Long Id;

    /** 角色名称 */
    @Schema(description = "角色名称", required = true)
    private String roleName;

    /** 角色标识 */
    @Schema(description = "角色标识", required = true)
    private String roleKey;

    /** 数据权限 */
    @Schema(description = "数据权限")
    private DataScope dataScope;

    /** 角色描述 */
    @Schema(description = "角色描述")
    private String description;

    /** 菜单权限ID */
    @Schema(description = "菜单权限ID数组", required = true)
    private Long[] menuIds;
} 