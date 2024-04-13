package com.vector.purchase.enums;

import lombok.Getter;

@Getter
public enum PurchaseOrderStatus {

    DRAFT("草稿"),
    APPROVING("待审批"),
    SIGNING("待签约"),
    PURCHASING("采购中"),
    FINISHED("已入库"),
    CANCELED("已取消"),
    REJECTED("已驳回");

    private final String desc;

    PurchaseOrderStatus(String desc){
        this.desc = desc;
    }

}
