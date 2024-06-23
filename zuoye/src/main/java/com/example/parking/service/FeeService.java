package com.example.parking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.parking.entity.CarFee;
import com.example.parking.entity.User;
import com.example.parking.entity.MsgVo;

/**
 * 车位收费信息 服务层
 */
public interface FeeService extends IService<CarFee> {

    /**
     * 更新车位收费费用
     *
     * @param fee 车位信息
     * @return 结果
     */
    MsgVo updateFee(CarFee fee);

    /**
     * 用户余额充值
     *
     * @param user 用户信息
     * @return 结果
     */
    MsgVo addUserFee(User user);
}
