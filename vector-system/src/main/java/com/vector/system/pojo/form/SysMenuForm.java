package com.vector.system.pojo.form;

import com.vector.system.enums.SysMenuPermission;
import com.vector.system.enums.SysMenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * vector-boot
 *
 * @author wengxs
 * @date 2024/5/1
 */
@Schema(description = "菜单表单对象")
@Data
public class SysMenuForm {

    /** 菜单ID */
    @Schema(description = "菜单ID")
    private Long id;

    /** 菜单名称 */
    @Schema(description = "菜单名称", required = true)
    private String menuName;

    /** 父级id */
    @Schema(description = "父级菜单ID")
    private Long parentId;

    /** 路径 */
    @Schema(description = "路由路径", required = true)
    private String path;

    /** 组件 */
    @Schema(description = "组件路径")
    private String component;

    /** 类型 */
    @Schema(description = "菜单类型", required = true)
    private SysMenuType type;

    /** 权限 */
    @Schema(description = "权限标识")
    private String permission;

    /** 图标 */
    @Schema(description = "菜单图标")
    private String icon;

    /** 排序 */
    @Schema(description = "显示顺序")
    private Integer sort;

    /** 隐藏菜单 */
    @Schema(description = "是否隐藏菜单")
    private Boolean hidden;

    /** 子权限 */
    @Schema(description = "子权限列表")
    private List<SysMenuPermission> subPermissions;
} 