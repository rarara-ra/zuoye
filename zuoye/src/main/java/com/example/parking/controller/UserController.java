package com.example.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.parking.entity.DateType;
import com.example.parking.entity.User;
import com.example.parking.entity.UserQuery;
import com.example.parking.entity.UserInfoVo;
import com.example.parking.service.UserService;
import com.example.parking.common.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关 控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 用户
     */
    @PostMapping("/login")
    public R<UserInfoVo> login(@RequestBody User user) {
        return R.success(userService.login(user));
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody User user) {
        return R.success(userService.register(user));
    }

    /**
     * 检查用户名
     *
     * @param username 用户名
     * @return 是否重复
     */
    @GetMapping("/checkUsername")
    public R<Boolean> checkUsername(String username) {
        return R.success(userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)) == null);
    }

    /**
     * 检查用户名
     *
     * @param card 车牌号
     * @return 是否重复
     */
    @GetMapping("/checkCard")
    public R<Boolean> checkCard(String card) {
        return R.success(userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getCard, card)) == null);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/add")
    public R<Boolean> addUser(@RequestBody User user) {
        return R.success(userService.add(user));
    }

    /**
     * 根据用户ID删除用户信息
     *
     * @param uid 用户id
     * @return 结果
     */
    @GetMapping("/del")
    public R<Boolean> delUser(Integer uid) {
        return R.success(userService.delUser(uid));
    }

    /**
     * 分页获取用户
     *
     * @param userQuery 条件信息
     * @return 用户信息
     */
    @PostMapping("/getUsers")
    public R<IPage<User>> getUsersPage(@RequestBody UserQuery userQuery) {
        return R.success(userService.getUsersPage(userQuery));
    }

    /**
     * 更改用户信息
     *
     * @param user 用户信息
     * @return 更新结果
     */
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        return R.success(userService.updateUser(user));
    }

    /**
     * 用户更改个人信息
     *
     * @param user 用户信息
     * @return 更新结果
     */
    @PostMapping("/updateByUser")
    public R<Boolean> updateByUser(@RequestBody User user) {
        return R.success(userService.updateByUser(user));
    }

    /**
     * 重置密码
     *
     * @param uid 用户id
     * @return 结果
     */
    @GetMapping("/resetPassword")
    public R<Boolean> resetPassword(Integer uid) {
        return R.success(userService.resetPassword(uid));
    }


    /**
     * 通过用户id查询
     *
     * @param uid 用户id
     * @return 用户
     */
    @GetMapping("/getUserByUid")
    public R<User> gerUserByUid(Integer uid) {
        return R.success(userService.getById(uid));
    }


    /**
     * 用户充值
     *
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/userPay")
    public R<Boolean> userPay(@RequestBody User user) {
        return R.success(userService.userPay(user));
    }

    /**
     * 获取用户停车类型记录
     *
     * @param person 用户
     * @return 停车位类型
     */
    @GetMapping("/getCarTypes")
    public R<List<DateType>> getCarTypes(String person) {
        return R.success(userService.getUserDataType(person));
    }

    /**
     * 获取用户缴费记录
     *
     * @param person 用户
     * @return 缴费记录
     */
    @GetMapping("/dataMoney")
    public R<List<DateType>> getUserMoney(String person) {
        return R.success(userService.getUserDataMoney(person));
    }

}

