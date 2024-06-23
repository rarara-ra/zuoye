package com.example.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.parking.entity.Stall;
import com.example.parking.entity.StallRes;
import com.example.parking.entity.StallCarQuery;
import com.example.parking.entity.StallQuery;
import com.example.parking.entity.StallResQuery;
import com.example.parking.entity.MsgVo;
import com.example.parking.service.StallService;
import com.example.parking.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车位 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stall")
public class StallController {

    private final StallService stallService;

    /**
     * 获取用户停泊车辆信息
     *
     * @param stallCarQuery 车辆信息
     * @return 停泊的车辆信息
     */
    @PostMapping("/pageStallCar")
    public R<IPage<Stall>> pageStallCar(@RequestBody StallCarQuery stallCarQuery) {
        return R.success(stallService.carPage(stallCarQuery));
    }

    /**
     * 获取车位信息列表
     *
     * @param stallQuery 车位信息
     * @return 车位信息
     */
    @PostMapping("/pageStall")
    public R<IPage<Stall>> pageStall(@RequestBody StallQuery stallQuery) {
        return R.success(stallService.pageStall(stallQuery));
    }

    /**
     * 预定车位
     *
     * @param uid 用户id
     * @param sid 车位id
     * @return 结果
     */
    @GetMapping("/orderStall")
    public R<Boolean> orderStall(Integer uid, Integer sid) {
        return R.success(stallService.orderStall(uid, sid));
    }

    /**
     * 添加车位
     *
     * @param stall 车位信息
     * @return 结果
     */
    @PostMapping("/add")
    public R<MsgVo> addStall(@RequestBody Stall stall) {
        return R.success(stallService.addStall(stall));
    }

    /**
     * 更新车位
     *
     * @param stall 车位信息
     * @return 结果
     */
    @PostMapping("/update")
    public R<MsgVo> updateStall(@RequestBody Stall stall) {
        return R.success(stallService.updateStall(stall));
    }

    /**
     * 删除车位 （逻辑删除）
     *
     * @param sid 车位id
     * @return 删除结果
     */
    @GetMapping("/del")
    public R<Boolean> deleteStall(Integer sid) {
        Stall stall = new Stall();
        stall.setSid(sid);
        //状态设置为逻辑删除
        stall.setStallLive("0");
        return R.success(stallService.updateById(stall));
    }

    /**
     * 获取用户所有停车记录
     *
     * @param person 用户名
     * @return 停车位记录
     */
    @GetMapping("/listUserStallRes")
    public R<List<StallRes>> listUserStallRes(String person) {
        return R.success(stallService.listUserStallRes(person));
    }

    /**
     * 获取用户所有未缴费记录
     *
     * @param person 用户名
     * @return 未缴费记录
     */
    @GetMapping("/allNoPay")
    public R<Object> allNoPay(String person) {
        return R.success(
                stallService.getAllNoPay(person)
                        .stream().filter(r -> r.getOverTime() == null)
        );
    }

    /**
     * 查询所有停车缴费记录
     *
     * @param stallResQuery 停车缴费信息
     * @return 停车缴费记录
     */
    @PostMapping("/allList")
    public R<IPage<StallRes>> listStallRes(@RequestBody StallResQuery stallResQuery) {
        return R.success(stallService.getAllListStallRes(stallResQuery));
    }

    /**
     * 停车缴费（管理员）
     *
     * @param stallRes 停车信息
     * @return 结果
     */
    @PostMapping("/payMoney")
    public R<Boolean> payMoney(@RequestBody StallRes stallRes) {
        return R.success(stallService.payMoneyManager(stallRes));
    }

    /**
     * 停车缴费（车主）
     *
     * @param stallRes 停车信息
     * @return 缴费结果
     */
    @PostMapping("/payMoneyPerson")
    public R<MsgVo> payMoneyPerson(@RequestBody StallRes stallRes) {
        return R.success(stallService.payMoneyPerson(stallRes));
    }

}

