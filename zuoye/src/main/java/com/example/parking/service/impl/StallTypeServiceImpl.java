package com.example.parking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.parking.entity.StallType;
import com.example.parking.service.StallTypeService;
import com.example.parking.mapper.StallTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 车位类型 服务层实现
 */
@Service
@RequiredArgsConstructor
public class StallTypeServiceImpl extends ServiceImpl<StallTypeMapper, StallType> implements StallTypeService {

}
