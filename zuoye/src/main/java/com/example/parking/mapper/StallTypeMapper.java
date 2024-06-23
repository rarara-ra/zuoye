package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parking.entity.StallType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位类型 持久层

 */
@Mapper
public interface StallTypeMapper extends BaseMapper<StallType> {

}
