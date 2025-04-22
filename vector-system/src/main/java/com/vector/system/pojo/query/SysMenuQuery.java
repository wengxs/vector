package com.vector.system.pojo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单查询参数
 * @author wengxs
 * @date 2024/5/12
 */
@Schema(description = "菜单查询参数")
@Data
public class SysMenuQuery {

    /** 菜单名称 */
    @Schema(description = "菜单名称")
    private String menuName;
}
