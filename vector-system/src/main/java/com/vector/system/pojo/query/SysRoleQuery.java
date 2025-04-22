package com.vector.system.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询参数
 * @author wengxs
 * @date 2024/5/12
 */
@Schema(description = "角色分页查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleQuery extends PageQuery {

    /** 角色名称 */
    @Schema(description = "角色名称")
    private String roleName;

    /** 角色编码 */
    @Schema(description = "角色编码")
    private String roleKey;
}
