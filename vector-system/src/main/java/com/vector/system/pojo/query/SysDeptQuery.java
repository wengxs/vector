package com.vector.system.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门 查询参数
 * @author wengxs
 */
@Schema(description = "部门分页查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptQuery extends PageQuery {

    /** 上级部门ID */
    @Schema(description = "上级部门ID")
    private Long parentId;

    /** 部门名称 */
    @Schema(description = "部门名称")
    private String deptName;

    /** 部门状态 */
    @Schema(description = "部门状态")
    private String deptStatus;
}
