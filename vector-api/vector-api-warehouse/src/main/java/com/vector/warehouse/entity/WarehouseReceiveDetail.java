package com.vector.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vector.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 收货单明细
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("warehouse_receive_detail")
public class WarehouseReceiveDetail extends BaseEntity<Long> {

    /** 收货单ID */
    private Long receiveId;

    /** 产品ID */
    private Long productId;

    /** 应收数量 */
    private Integer qty;

    /** 实收数量 */
    private Integer receivedQty;

    /** 合格数量 */
    private Integer qualifiedQty;

    /** 不合格数量 */
    private Integer unqualifiedQty;

}
