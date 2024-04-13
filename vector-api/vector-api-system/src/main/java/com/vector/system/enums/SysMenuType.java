package com.vector.system.enums;

public enum SysMenuType {

    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private final int code;
    private final String desc;
    private SysMenuType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SysMenuType get(int code) {
        for (SysMenuType value : values()) {
            if (value.code == code) return value;
        }
        return null;
    }
}
