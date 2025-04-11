package com.vector.sale.enums;

import lombok.Getter;

@Getter
public enum SalePlatform {

    UN("未知"),
    TB("淘宝"),
    PDD("拼多多"),
    JD("京东");

    private final String desc;

    SalePlatform(String desc){
        this.desc = desc;
    }
}
