package com.example.parking.controller;


import com.example.parking.entity.StallType;
import com.example.parking.service.StallTypeService;
import com.example.parking.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 车位类型 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stallType")
public class StallTypeController {

    private final StallTypeService stallTypeService;

    /**
     * 查询车位类型
     *
     * @return 车位类型
     */
    @GetMapping("/list")
    public R<List<StallType>> getList() {
        return R.success(stallTypeService.list());
    }

}

