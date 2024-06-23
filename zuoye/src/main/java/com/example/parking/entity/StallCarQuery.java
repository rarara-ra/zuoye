package com.example.parking.entity;

import lombok.Data;

/**
 * 停车位信息 查询对象
 */
@Data
public class StallCarQuery {

    /**
     * 名称
     */
    private String nike;

    /**
     * 身份证号码
     */
    private String card;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 分页页码
     */
    private Integer pagenum;

}
