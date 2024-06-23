package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parking.entity.CarFee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆收费信息 持久层
 */
@Mapper
public interface FeeMapper extends BaseMapper<CarFee> {
}
