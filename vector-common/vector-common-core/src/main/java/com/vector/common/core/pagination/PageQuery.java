package com.vector.common.core.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 查询参数基类
 * @author wengxs
 * @date 2024/5/4
 */
@Schema
@Data
public class PageQuery {

    /** 分码 */
    @Schema(description = "页码", example = "1")
    protected Integer page;

    /** 分页大小 */
    @Schema(description = "分页大小", example = "20")
    protected Integer pageSize;

    /** 排序字段 */
    @Schema(description = "排序字段", example = "id")
    protected String sortBy;

    /** 排序方式 */
    @Schema(description = "排序方式", example = "desc")
    protected String sortDirection;

    /** 创建时间(起) */
    @Schema(description = "创建时间(起)", example = "2025-01-01")
    private String createBegin;

    /** 创建时间(止) */
    @Schema(description = "创建时间(止)", example = "2025-01-31")
    private String createEnd;
}
