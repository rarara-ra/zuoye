package com.example.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parking.entity.Stall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 停车位 持久层
 */
@Mapper
public interface StallMapper extends BaseMapper<Stall> {

    /**
     * 设置车位空闲状态
     *
     * @param sid 车位id
     * @return 结果
     */
    Integer setStallOrg(@Param("sid") Integer sid);

    /**
     * 获取用户停泊车辆信息
     *
     * @param page 分页信息
     * @param nike 昵称
     * @param card 车牌号
     * @return 停泊的车辆信息
     */
    IPage<Stall> getStallAll(Page<Stall> page, @Param("nike") String nike, @Param("card") String card);
}
