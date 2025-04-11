package com.vector.sale.form;

import com.vector.sale.enums.SalePlatform;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleOrderForm {

    private Long id;

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

    /** 商品明细 */
    private List<Detail> details;

    @Data
    public static class Detail {

        /** 产品ID */
        private Long productId;

        /** 产品名称 */
        private String productName;

        /** 单价 */
        private BigDecimal price;

        /** 采购数量 */
        private Integer qty;
    }
}
