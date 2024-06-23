package com.example.parking.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.parking.entity.CarFee;
import com.example.parking.entity.User;
import com.example.parking.entity.MsgVo;
import com.example.parking.service.FeeService;
import com.example.parking.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车位费用 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    /**
     * 查询车位类型收费信息
     *
     * @param carType 车位类型
     * @return 缴费记录
     */
    @GetMapping("/list")
    public R<List<CarFee>> listFee(String carType) {
        return R.success(feeService.list(Wrappers.<CarFee>lambdaQuery().like(CarFee::getCarType, carType)));
    }

    /**
     * 更新车位收费费用
     *
     * @param fee 车位信息
     * @return 结果
     */
    @PostMapping("update")
    public R<MsgVo> updateFee(@RequestBody CarFee fee) {
        return R.success(feeService.updateFee(fee));
    }


    /**
     * 用户余额充值
     *
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/userFee")
    public R<Object> feeUser(@RequestBody User user) {
        return R.success(feeService.addUserFee(user));
    }

}

