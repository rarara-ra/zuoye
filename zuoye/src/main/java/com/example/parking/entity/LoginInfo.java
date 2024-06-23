package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录信息
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志编号
     */
    @TableId(value = "yid", type = IdType.AUTO)
    private Integer yid;

    /**
     * 用户
     */
    private String person;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

}
