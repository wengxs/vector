package com.vector.system.pojo.vo;

import com.vector.system.enums.SysMenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单视图
 * @author wengxs
 */
@Schema(description = "菜单视图对象")
@Data
public class SysMenuVO {

    /** 菜单ID */
    @Schema(description = "菜单ID")
    private Long id;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 创建人 */
    @Schema(description = "创建人")
    private Long createBy;

    /** 菜单名称 */
    @Schema(description = "菜单名称")
    private String menuName;

    /** 父级id */
    @Schema(description = "父级菜单ID")
    private Long parentId;

    /** 路径 */
    @Schema(description = "路由路径")
    private String path;

    /** 组件 */
    @Schema(description = "组件路径")
    private String component;

    /** 类型 */
    @Schema(description = "菜单类型")
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

    /** 子菜单 */
    @Schema(description = "子菜单列表")
    private List<SysMenuVO> children;
}
