package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parking.entity.Car;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆 持久层
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
