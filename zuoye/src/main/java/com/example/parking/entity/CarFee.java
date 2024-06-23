package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 车位收费信息
 */
@Data
public class CarFee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收费标准ID
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * 车位类型
     */
    private String carType;

    /**
     * 价格
     */
    private Double money;

    /**
     * 价格描述
     */
    private String moneyDesc;

    /**
     * 创建时间
     */
    private LocalDateTime feeTime;

}
