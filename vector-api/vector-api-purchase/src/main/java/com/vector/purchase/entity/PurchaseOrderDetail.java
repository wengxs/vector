package com.vector.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import com.vector.purchase.enums.PurchaseOrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("purchase_order_detail")
public class PurchaseOrderDetail extends BaseEntity<Long> {

    /** 采购单ID */
    private Long orderId;

    /** 产品ID */
    private Long productId;

    /** 单价 */
    private BigDecimal price;

    /** 采购数量 */
    private Integer qty;

    /** 总金额 */
    private BigDecimal amount;

    /** 收货数量 */
    private Integer receivedQty;

}
