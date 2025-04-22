package com.vector.system.pojo.vo;

import com.vector.common.core.enums.DataScope;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色视图
 * @author wengxs
 */
@Schema(description = "角色视图对象")
@Data
public class SysRoleVO {

    /** 角色ID */
    @Schema(description = "角色ID")
    private Long id;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 创建人 */
    @Schema(description = "创建人")
    private Long createBy;

    /** 角色名称 */
    @Schema(description = "角色名称")
    private String roleName;

    /** 角色编码 */
    @Schema(description = "角色编码")
    private String roleKey;

    /** 数据权限 */
    @Schema(description = "数据权限")
    private DataScope dataScope;

    /** 角色描述 */
    @Schema(description = "角色描述")
    private String description;

    /** 菜单权限ID */
    @Schema(description = "菜单权限ID集合")
    private List<Long> menuIds;
}
