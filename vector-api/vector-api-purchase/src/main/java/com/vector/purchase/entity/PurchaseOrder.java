package com.vector.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vector.common.core.entity.BaseEntity;
import com.vector.purchase.enums.PurchaseOrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("purchase_order")
public class PurchaseOrder extends BaseEntity<Long> {

    /** 采购单号 */
    private String orderNo;

    /** 订单状态 */
    private PurchaseOrderStatus orderStatus;

    /** 供应商ID */
    private Long supplierId;

    /** 采购备注 */
    private String purchaseRemake;

    /** 订单金额 */
    private BigDecimal amount;

    /** 签约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signedTime;

    /** 签约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishedTime;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date canceledTime;

}
