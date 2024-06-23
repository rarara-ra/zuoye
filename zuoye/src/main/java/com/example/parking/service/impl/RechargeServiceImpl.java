package com.example.parking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.parking.entity.Recharge;
import com.example.parking.service.RechargeService;
import com.example.parking.mapper.RechargeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 充值 服务层实现类
 */
@Service
@RequiredArgsConstructor
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

}
