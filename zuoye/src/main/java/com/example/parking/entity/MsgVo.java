package com.example.parking.entity;

import lombok.Data;

/**
 * 消息对象 vo
 * 兼容接口消息用
 */
@Data
public class MsgVo {

    /**
     * 消息状态
     */
    private Boolean flag;

    /**
     * 消息文案
     */
    private String msg;

    public MsgVo(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

}

