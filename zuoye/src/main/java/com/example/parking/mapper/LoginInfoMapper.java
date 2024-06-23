package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parking.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录信息 持久层
 */
@Mapper
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

}
