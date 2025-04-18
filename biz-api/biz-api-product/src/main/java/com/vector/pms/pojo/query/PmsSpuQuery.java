package com.vector.pms.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import com.vector.pms.enums.PmsSpuStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品信息 VO
 * @author wengxs
 * @date 2024/05/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSpuQuery extends PageQuery {

    /** 商品标题 */
    private String spuName;

    /** 商品状态 */
    private PmsSpuStatus spuStatus;

    /** 分类ID */
    private Long categoryId;

    /** 分类名称 */
    private String categoryName;
}
