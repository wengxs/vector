package com.vector.core;

import com.vector.core.enums.ErrorCode;

import java.io.Serializable;

/**
 * @author Zorg
 * @date 2022/3/24
 */
public class R implements Serializable {

    public static final int FAIL = ErrorCode.FAILURE.getCode();

    private int code = ErrorCode.SUCCESS.getCode();

    private String massage = ErrorCode.SUCCESS.getDesc();

    private Object data;

    public R() {
    }

    public R(int code, String massage, Object data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public static R ok() {
        return ok(null);
    }

    public static R ok(Object data) {
        R r = new R();
        r.setData(data);
        return r;
    }

    public static R fail(int code, String massage, Object data) {
        return new R(code, massage, data);
    }

    public static R fail(int code, String massage) {
        return R.fail(code, massage, null);
    }

    public static R fail(String massage) {
        return R.fail(FAIL, massage);
    }

    public static R fail(Throwable e) {
        return fail(e.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
