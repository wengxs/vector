package com.vector.warehouse.enums;

import lombok.Getter;

@Getter
public enum WarehouseReceiveStatus {

    UNSENT("待发货"),
    SENT_OUT("待收货"),
    SIGNED("已签收"),
    FINISHED("已入库"),
    CANCELED("已取消");

    private final String desc;

    WarehouseReceiveStatus(String desc){
        this.desc = desc;
    }

}
