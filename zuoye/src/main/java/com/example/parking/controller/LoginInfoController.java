package com.example.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.parking.entity.LoginInfo;
import com.example.parking.entity.LoginInfoQuery;
import com.example.parking.service.LoginInfoService;
import com.example.parking.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录信息 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login-info")
public class LoginInfoController {

    private final LoginInfoService loginInfoService;

    /**
     * 获取登录用户列表
     *
     * @param loginInfoQuery 用户信息
     * @return 用户列表
     */
    @PostMapping("/getLoginInfoList")
    public R<IPage<LoginInfo>> getStallList(@RequestBody LoginInfoQuery loginInfoQuery) {
        return R.success(loginInfoService.getLoginInfoList(loginInfoQuery));
    }
}

