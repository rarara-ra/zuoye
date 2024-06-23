package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parking.entity.StallRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 停车记录 持久层
 */
@Mapper
public interface StallResMapper extends BaseMapper<StallRes> {

    /**
     * 获取用户所有停车记录
     *
     * @param person 用户名
     * @return 停车记录
     */
    List<StallRes> getAllStallRes(@Param("person") String person);

    /**
     * 获取所有停车记录
     *
     * @param person 用户名
     * @return 停车记录
     */
    IPage<StallRes> getAllListStallRes(Page<StallRes> page, @Param("person") String person,
                                       @Param("inTime") LocalDateTime inTime,
                                       @Param("outTime") LocalDateTime outTime,
                                       @Param("stallArea") String stallArea);
}
