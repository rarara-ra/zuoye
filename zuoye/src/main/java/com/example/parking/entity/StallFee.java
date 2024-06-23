package com.example.parking.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 停车位费用缴费统计数据
 */
@Data
public class StallFee {

    /**
     * 名称
     */
    private LocalDateTime name;

    /**
     * 值
     */
    private Double value;

}
