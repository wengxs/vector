package com.vector.sale.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vector.sale.enums.SaleOrderStatus;
import com.vector.sale.enums.SalePlatform;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleOrderVo {

    /** ID */
    private Long id;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 创建人 */
    private String createBy;

    /** 销售单号 */
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

    /** 创建时间：起 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createBegin;

    /** 创建时间：止 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createEnd;

    /** 产品ID */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long productId;

    /** 产品名称 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String productName;

    /** 订单明细 */
    private List<Detail> details;

    @Data
    public static class Detail {

        /** 明细ID */
        private Long id;

        /** 销售单ID */
        private Long orderId;

        /** 产品ID */
        private Long productId;

        /** 产品名称 */
        private Long productName;

        /** 商品单价 */
        private BigDecimal price;

        /** 购买数量 */
        private Integer qty;

        /** 总金额 */
        private BigDecimal amount;

    }
}
