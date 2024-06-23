package com.example.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.parking.entity.*;
import com.example.parking.entity.MsgVo;
import com.example.parking.mapper.FeeMapper;
import com.example.parking.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 车位收费信息 服务层实现类
 */
@Service
@RequiredArgsConstructor
public class FeeServiceImpl extends ServiceImpl<FeeMapper, CarFee> implements FeeService {

    private final StallTypeService stallTypeService;

    private final StallService stallService;

    private final UserService userService;

    private final RechargeService rechargeService;

    @Override
    public MsgVo updateFee(CarFee fee) {
        if (StrUtil.isNotBlank(fee.getCarType()) && fee.getMoney() != null) {
            Boolean updateStatus = this.update(
                    fee, Wrappers.<CarFee>lambdaQuery().eq(CarFee::getCarType, fee.getCarType()));

            //更新成功
            if (updateStatus) {
                //更新车位收费
                Stall stall = new Stall();
                stall.setStallType(fee.getCarType());
                stall.setStallMoney(fee.getMoney());
                stallService.update(stall, Wrappers.<Stall>lambdaQuery().eq(Stall::getStallType, fee.getCarType()));

                //更新车位价格
                StallType stallType = new StallType();
                stallType.setOmoney(fee.getMoney());
                stallType.setOtype(fee.getCarType());
                stallTypeService.update(stallType, Wrappers.<StallType>lambdaQuery().eq(StallType::getOtype,
                        fee.getCarType()));

                return new MsgVo(true, "更新成功");
            }
        } else {
            return new MsgVo(false, "更新失败，填写不完善");
        }

        return new MsgVo(false, "更新失败，请重试");
    }

    @Override
    public MsgVo addUserFee(User user) {
        if (StrUtil.isNotBlank(user.getUsername()) && user.getMoney() != null) {
            //查询用户
            User existUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));

            //用户不存在
            if (existUser == null) {
                return new MsgVo(false, "充值失败,用户不存在");
            }

            Double mon = user.getMoney();
            Double non = existUser.getMoney() == null ? 0 : existUser.getMoney();

            //更新充值余额
            user.setMoney(non + user.getMoney());
            Boolean updateStatus = userService.update(
                    user, Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));

            if (updateStatus) {
                //保存充值记录
                Recharge recharge = new Recharge();
                recharge.setCtime(LocalDateTime.now());
                recharge.setPerson(user.getUsername());
                recharge.setMoney(mon);
                rechargeService.save(recharge);

                return new MsgVo(true, "充值成功");
            }
        }

        return new MsgVo(false, "充值失败，填写不完善");

    }
}
