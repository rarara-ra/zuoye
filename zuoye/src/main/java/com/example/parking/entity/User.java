package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户信息
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nike;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 车牌号
     */
    private String card;

    /**
     * 余额
     */
    private Double money;

    /**
     * 角色。0系统管理员，1车主
     */
    private Integer role;

    /**
     * 缴费扣费
     *
     * @param payMoney 缴费金额
     */
    public void updateMoney(Double payMoney) {
        this.money = this.money - payMoney;
    }

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password,
                user.password) && Objects.equals(nike, user.nike) && Objects.equals(age, user.age) && Objects.equals(sex, user.sex) && Objects.equals(phone, user.phone) && Objects.equals(card, user.card) && Objects.equals(money, user.money) && Objects.equals(role, user.role) && Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, nike, age, sex, phone, card, money, role, createTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nike='" + nike + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", card='" + card + '\'' +
                ", money=" + money +
                ", role=" + role +
                ", createTime=" + createTime +
                '}';
    }
}
