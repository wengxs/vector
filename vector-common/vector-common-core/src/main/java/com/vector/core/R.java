package com.vector.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vector.core.enums.ErrorCode;

import java.io.Serializable;

/**
 * @author Zorg
 * @date 2022/3/24
 */
public class R<T> implements Serializable {

    public static final int SUCCESS = ErrorCode.SUCCESS.getCode();

    public static final int FAIL = ErrorCode.FAILURE.getCode();

    private int code = SUCCESS;

    private String massage = ErrorCode.SUCCESS.getDesc();

    private T data;

    public R() {
    }

    public R(int code, String massage, T data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(int code, String massage, T data) {
        return new R<>(code, massage, data);
    }

    public static <T> R<T> fail(int code, String massage) {
        return R.fail(code, massage, null);
    }

    public static <T> R<T> fail(String massage) {
        return R.fail(FAIL, massage);
    }

    public static <T> R<T> fail(Throwable e) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isOk() {
        return this.code == SUCCESS;
    }
}
