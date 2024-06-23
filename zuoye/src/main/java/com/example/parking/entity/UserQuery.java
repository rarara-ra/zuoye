package com.example.parking.entity;


import lombok.Data;

/**
 * 用户信息 查询对象
 */
@Data
public class UserQuery {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nike;

    /**
     * 证件号码
     */
    private String card;

    /**
     * 分页页数
     */
    private Integer pagenum;

    /**
     * 分页大小
     */
    private Integer pageSize;

}
