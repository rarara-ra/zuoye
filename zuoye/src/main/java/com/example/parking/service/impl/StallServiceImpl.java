package com.example.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.parking.entity.*;
import com.example.parking.entity.StallCarQuery;
import com.example.parking.entity.StallQuery;
import com.example.parking.entity.StallResQuery;
import com.example.parking.entity.MsgVo;
import com.example.parking.mapper.*;
import com.example.parking.service.StallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车位 服务层实现类
 */
@Service
@RequiredArgsConstructor
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements StallService {

    private final StallMapper stallMapper;

    private final StallResMapper stallResMapper;

    private final StallTypeMapper stallTypeMapper;

    private final UserMapper userMapper;

    private final RechargeMapper rechargeMapper;

    @Override
    public IPage<Stall> pageStall(StallQuery stallQuery) {
        //分页条件
        Page<Stall> page = new Page<>(stallQuery.getPagenum(), stallQuery.getPageSize());

        //查询条件
        LambdaQueryWrapper<Stall> lambdaQuery = Wrappers.<Stall>lambdaQuery()
                //车位
                .eq(StrUtil.isNotBlank(stallQuery.getCarArea()), Stall::getStallArea, stallQuery.getCarArea())
                //车位状态
                .eq(StrUtil.isNotBlank(stallQuery.getCarState()), Stall::getStallState, stallQuery.getCarState())
                //车位类型
                .eq(StrUtil.isNotBlank(stallQuery.getCarType()), Stall::getStallType, stallQuery.getCarType())
                //未删除
                .eq(Stall::getStallLive, "1");

        //分页查询
        return this.page(page, lambdaQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean orderStall(Integer uid, Integer sid) {
        //查询车位信息
        Stall stall = this.getById(sid);

        //判断没有人停车
        if (stall.getUserId() == null) {

            //更新车位状态
            stall.setUserId(uid);
            stall.setStallState("已停车");
            stallMapper.updateById(stall);

            //添加个人停车记录
            StallRes stallRes = new StallRes();
            User user = userMapper.selectById(uid);
            stallRes.setPerson(user.getUsername());
            stallRes.setStallId(sid);
            stallRes.setCreateTime(LocalDateTime.now());
            stallResMapper.insert(stallRes);

            return true;
        }

        return false;
    }

    @Override
    public MsgVo addStall(Stall stall) {
        //查询车位类型
        StallType stallType = stallTypeMapper.selectOne(
                Wrappers.<StallType>lambdaQuery().eq(StallType::getOtype,
                        stall.getStallType())
        );

        //查询已存在车位信息
        Stall existStall = stallMapper.selectOne(
                Wrappers.<Stall>lambdaQuery()
                        .eq(Stall::getStallNum, stall.getStallNum())
                        .eq(Stall::getStallArea, stall.getStallArea())
                        .eq(Stall::getStallType, stall.getStallType()));

        //车位不存在
        if (existStall == null) {
            //设置车位状态
            stall.setStallState("空闲中");
            stall.setStallLive("1");
            stall.setStallMoney(stallType.getOmoney());

            //保存车位
            if (this.save(stall)) {
                return new MsgVo(true, "添加成功");
            } else {
                return new MsgVo(false, "添加失败，请重新尝试");
            }
        } else {
            return new MsgVo(false, "该车位已经存在");
        }
    }

    @Override
    public MsgVo updateStall(Stall stall) {
        //查询已存在车位
        Stall existStall = stallMapper.selectOne(
                Wrappers.<Stall>lambdaQuery()
                        .eq(Stall::getStallNum, stall.getStallNum())
                        .eq(Stall::getStallArea, stall.getStallArea()));

        //车位存在才能更新
        if (existStall != null) {
            if (this.updateById(stall)) {
                return new MsgVo(true, "修改成功");
            } else {
                return new MsgVo(false, "修改失败，请重新尝试");
            }
        } else {
            return new MsgVo(false, "修改失败，车位不存在");
        }

    }

    @Override
    public List<StallRes> listUserStallRes(String person) {
        return stallResMapper.getAllStallRes(person);
    }

    @Override
    public IPage<StallRes> getAllListStallRes(StallResQuery stallResQuery) {
        //分页条件
        Page<StallRes> page = new Page<>(stallResQuery.getPagenum(), stallResQuery.getPageSize());

        return stallResMapper.getAllListStallRes(
                page, stallResQuery.getPerson(), stallResQuery.getInTime(),
                stallResQuery.getOutTime(), stallResQuery.getStallArea()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsgVo payMoneyPerson(StallRes stallRes) {
        //查询车主用户
        User user = userMapper.selectOne(
                Wrappers.<User>lambdaQuery().eq(User::getUsername, stallRes.getPerson())
        );

        if (user == null) {
            return new MsgVo(false, "缴费失败");
        }

        if (user.getMoney() >= stallRes.getMoney()) {
            //更新用户余额
            user.setMoney(user.getMoney() - stallRes.getMoney());
            userMapper.updateById(user);

            //设置车位空闲状态
            stallMapper.setStallOrg(stallRes.getStallId());

            //更新停车记录
            StallRes userStallRes = new StallRes();
            userStallRes.setPid(stallRes.getPid());
            userStallRes.setOverTime(LocalDateTime.now());
            userStallRes.setMoney(stallRes.getMoney());
            stallResMapper.updateById(userStallRes);

            //保存缴费记录
            Recharge recharge = new Recharge();
            recharge.setMoney(stallRes.getMoney());
            recharge.setPerson(stallRes.getPerson());
            recharge.setCtime(LocalDateTime.now());
            rechargeMapper.insert(recharge);

            return new MsgVo(true, "缴费成功");
        } else {
            return new MsgVo(false, "余额不足，请先充值");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean payMoneyManager(StallRes stallRes) {
        //查询车主用户
        User carUser = userMapper.selectOne(
                Wrappers.<User>lambdaQuery().eq(User::getUsername, stallRes.getPerson())
        );

        if (carUser == null) {
            return false;
        }

        //更新用户余额
        carUser.updateMoney(stallRes.getMoney());
        userMapper.updateById(carUser);

        //设置车位空闲状态
        stallMapper.setStallOrg(stallRes.getStallId());

        //更新停车记录
        StallRes userStrllRes = new StallRes();
        userStrllRes.setPid(stallRes.getPid());
        userStrllRes.setOverTime(LocalDateTime.now());
        userStrllRes.setMoney(stallRes.getMoney());
        stallResMapper.updateById(userStrllRes);

        //保存缴费记录
        Recharge recharge = new Recharge();
        recharge.setMoney(stallRes.getMoney());
        recharge.setPerson(stallRes.getPerson());
        recharge.setCtime(LocalDateTime.now());
        rechargeMapper.insert(recharge);

        return true;
    }


    @Override
    public List<StallRes> getAllNoPay(String person) {
        return stallResMapper.getAllStallRes(person);
    }

    @Override
    public IPage<Stall> carPage(StallCarQuery stallCarQuery) {
        Page<Stall> page = new Page<>(stallCarQuery.getPagenum(), stallCarQuery.getPageSize());
        return stallMapper.getStallAll(page, stallCarQuery.getNike(), stallCarQuery.getCard());
    }

}
