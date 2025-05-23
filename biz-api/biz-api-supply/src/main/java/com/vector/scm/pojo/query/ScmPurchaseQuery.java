package com.vector.scm.pojo.query;

import com.vector.common.core.pagination.PageQuery;
import com.vector.scm.enums.ScmPurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 采购单 查询参数
 * @author wengxs
 * @date 2024/05/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScmPurchaseQuery extends PageQuery {

    /** 采购单号 */
    private String purchaseNo;

    /** 订单状态 */
    private ScmPurchaseStatus purchaseStatus;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 产品ID */
    private Long productId;

    /** 产品名称 */
    private String productName;
}
