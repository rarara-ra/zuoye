package com.example.parking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.parking.entity.Stall;
import com.example.parking.entity.StallRes;
import com.example.parking.entity.StallQuery;
import com.example.parking.entity.MsgVo;
import com.example.parking.entity.StallCarQuery;
import com.example.parking.entity.StallResQuery;

import java.util.List;

/**
 * 车位 服务层
 */
public interface StallService extends IService<Stall> {

    /**
     * 分页获取
     *
     * @param stallQuery 参数
     * @return 车位信息
     */
    IPage<Stall> pageStall(StallQuery stallQuery);

    /**
     * 预定车位
     *
     * @param uid 用户编号
     * @param sid 车位编号
     * @return 结果
     */
    Boolean orderStall(Integer uid, Integer sid);

    /**
     * 添加车位
     *
     * @param stall 车位信息
     * @return 结果
     */
    MsgVo addStall(Stall stall);

    /**
     * 更新车位
     *
     * @param stall 车位信息
     * @return Object
     */
    MsgVo updateStall(Stall stall);

    /**
     * 获取用户所有停车记录
     *
     * @param person 用户
     * @return Object
     */
    List<StallRes> listUserStallRes(String person);

    /**
     * 获取所有停车记录
     *
     * @param stallResQuery 查询信息
     * @return 停车记录
     */
    IPage<StallRes> getAllListStallRes(StallResQuery stallResQuery);

    /**
     * 停车缴费 （车主）
     *
     * @param stallRes 停车信息
     * @return 结果
     */
    MsgVo payMoneyPerson(StallRes stallRes);

    /**
     * 停车缴费 （管理员）
     *
     * @param stallRes 停车信息
     * @return 结果
     */
    Boolean payMoneyManager(StallRes stallRes);

    /**
     * 获取用户所有未缴费记录
     *
     * @param person 用户名
     * @return 未缴费记录
     */
    List<StallRes> getAllNoPay(String person);

    /**
     * 获取用户停泊车辆信息
     *
     * @param stallCarQuery 车辆信息
     * @return 停泊的车辆信息
     */
    IPage<Stall> carPage(StallCarQuery stallCarQuery);
}
