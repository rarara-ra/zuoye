package com.example.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 停车位类型
 */
@Data
public class StallType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车位类型编号
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 车位类型
     */
    private String otype;

    /**
     * 车位价格
     */
    private Double omoney;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StallType stallType = (StallType) o;
        return Objects.equals(oid, stallType.oid) && Objects.equals(otype, stallType.otype) && Objects.equals(omoney, stallType.omoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, otype, omoney);
    }

    @Override
    public String toString() {
        return "StallType{" +
                "oid=" + oid +
                ", otype='" + otype + '\'' +
                ", omoney=" + omoney +
                '}';
    }
}
