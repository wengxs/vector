package com.vector.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by Zorg
 * 2020/5/15 23:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity<Long> {

    /** 菜单名称 */
    private String name;
    /** 父级id */
    private Long parentId;
    /** 路径 */
    private String path;
    /** 组件 */
    private String component;
    /** 类型 */
    private Integer type;
    /** 权限 */
    private String permission;
    /** 图标 */
    private String icon;
    /** 排序 */
    private Integer sort;

    @TableField(exist = false)
    private List<String> subPermissions;

}
