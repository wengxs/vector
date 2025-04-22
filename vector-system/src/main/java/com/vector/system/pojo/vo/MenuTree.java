package com.vector.system.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author wengxs
 */
@Schema(description = "菜单树视图")
@Data
public class MenuTree {

    /** 菜单ID */
    @Schema(description = "菜单ID")
    private Long id;

    /** 菜单名称 */
    @Schema(description = "菜单名称")
    private String menuName;

    /** 子菜单 */
    @Schema(description = "子菜单树")
    private List<MenuTree> children;
}
