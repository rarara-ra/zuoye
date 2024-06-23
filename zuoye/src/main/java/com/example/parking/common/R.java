package com.example.parking.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回包装
 */
@Data
public class R<T> implements Serializable {

    /**
     * 自定义标识
     */
    private Boolean flag;

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    private R(Boolean flag, String code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    private R(Boolean flag, String code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> success(T data) {
        return new R<T>(ResultCode.SUCCESS.getState(), ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                data);
    }

    public static <T> R<T> success() {
        return new R<T>(ResultCode.SUCCESS.getState(), ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static <T> R<T> error(T data) {
        return new R<T>(ResultCode.ERROR.getState(), ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage(), data);
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

}
