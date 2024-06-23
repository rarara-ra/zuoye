package com.example.parking.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 停车记录信息 查询对象
 */
@Data
public class StallResQuery {

    /**
     * 用户名
     */
    private String person;

    /**
     * 停车区域
     */
    private String stallArea;

    /**
     * 进入时间
     */
    private LocalDateTime inTime;

    /**
     * 出去时间
     */
    private LocalDateTime outTime;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 分页页数
     */
    private Integer pagenum;

}
