package com.example.parking.common;


/**
 * 接口返回状态码
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(true, "200", "成功"),

    /**
     * 失败
     */
    FAIL(false, "555", "失败"),

    /**
     * 系统异常
     */
    ERROR(false, "666", "系统发生异常");

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态消息
     */
    private String message;

    ResultCode(Boolean state, String code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
