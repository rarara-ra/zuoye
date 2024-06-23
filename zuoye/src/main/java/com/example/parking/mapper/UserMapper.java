package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parking.entity.DateType;
import com.example.parking.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息 持久层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户停车类型记录
     *
     * @param person 用户
     * @return 停车位类型
     */
    List<DateType> getUserDataType(@Param("person") String person);

    /**
     * 获取用户缴费记录
     *
     * @param person 用户
     * @return 缴费记录
     */
    List<DateType> getUserDataMoney(@Param("person") String person);
}
