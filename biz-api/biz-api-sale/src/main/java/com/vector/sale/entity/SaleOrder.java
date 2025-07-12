package com.vector.sale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vector.common.core.entity.BaseEntity;
import com.vector.sale.enums.SaleOrderStatus;
import com.vector.sale.enums.SalePlatform;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sale_order")
public class SaleOrder extends BaseEntity<Long> {

    /** 采购单号 */
    private String orderNo;

    /** 订单状态 */
    private SaleOrderStatus orderStatus;

    /** 销售平台 */
    private SalePlatform platform;

    /** 店铺名称 */
    private String shopName;

    /** 买家名称 */
    private String buyer;

    /** 买家电话 */
    private String mobile;

    /** 买家地区编码 */
    private String areaCode;

    /** 买家地区 */
    private String area;

    /** 买家地址 */
    private String address;

    /** 订单备注 */
    private String orderRemake;

    /** 订单金额 */
    private BigDecimal amount;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTime;

    /** 签收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;

    /** 完结时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

}