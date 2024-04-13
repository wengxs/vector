package com.vector.sale.enums;

import lombok.Getter;

@Getter
public enum SaleOrderStatus {

    UNPAID("未付款"),
    PAID("已付款"),
    SHIPPED("已发货"),
    SIGNED("已签收"),
    FINISHED("已完结"),
    CANCELED("已取消");

    private final String desc;

    SaleOrderStatus(String desc){
        this.desc = desc;
    }

}
