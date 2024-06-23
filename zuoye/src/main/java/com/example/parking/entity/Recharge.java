package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 充值
 */
@Data
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 用户
     */
    private String person;

    /**
     * 充值金额
     */
    private Double money;

    /**
     * 充值时间
     */
    private LocalDateTime ctime;

}
