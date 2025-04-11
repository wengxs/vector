package com.vector.pms.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品信息 VO
 * @author wengxs
 * @date 2024/05/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsProductQuery extends PageQuery {

    /** 产品名称 */
    private String productName;

    /** 生产厂家 */
    private String factory;
}
