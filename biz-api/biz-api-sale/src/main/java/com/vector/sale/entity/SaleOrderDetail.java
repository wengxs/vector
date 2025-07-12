package com.vector.sale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 销售单明细
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sale_order_detail")
public class SaleOrderDetail extends BaseEntity<Long> {

    /** 销售单ID */
    private Long orderId;

    /** 产品ID */
    private Long productId;

    /** 商品单价 */
    private BigDecimal price;

    /** 购买数量 */
    private Integer qty;

    /** 总金额 */
    private BigDecimal amount;

}
