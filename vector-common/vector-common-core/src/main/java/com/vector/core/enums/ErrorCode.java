package com.vector.core.enums;

/**
 * 错误码
 * @author Zorg
 * @date 2022/3/24
 */
public enum ErrorCode {

    // 错误码
    SUCCESS(200, "操作成功"),
    FAILURE(500, "操作失败");

    private final Integer code;
    private final String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 获取
     */
    public static ErrorCode get(Integer code) {
        for (ErrorCode value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IndexOutOfBoundsException("未知枚举值：" + code);
    }
}
