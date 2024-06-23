package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.parking.entity.Stall;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 停车记录
 */
@Data
@TableName("stall_res")
public class StallRes {

    /**
     * 主键
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 用户名
     */
    @TableField("person")
    private String person;

    /**
     * 车位id
     */
    @TableField("stall_id")
    private Integer stallId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 结束时间
     */
    @TableField("over_time")
    private LocalDateTime overTime;

    /**
     * 缴费金额
     */
    @TableField("pay_money")
    private Double money;

    /**
     * 车位
     */
    @TableField(exist = false)
    private Stall stall;

}

