package com.vector.system.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典 查询参数
 * @author wengxs
 */
@Schema(description = "字典分页查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictQuery extends PageQuery {

    /** 字典名称 */
    @Schema(description = "字典名称")
    private String dictName;

    /** 字典键 */
    @Schema(description = "字典键")
    private String dictKey;

    /** 字典状态 */
    @Schema(description = "字典状态")
    private String dictStatus;
}
