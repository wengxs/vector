package com.vector.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 * @author wengxs
 */
@AllArgsConstructor
@Getter
public enum SysUserStatus {

    DISABLED("禁用"),
    ENABLED("启用");

    private final String desc;
}
