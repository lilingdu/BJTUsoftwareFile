package com.work.information.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: miki
 * @Date: 2021/07/24/8:35
 * @Description:
 */

@Table(name = "passenger")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;//乘客id

    @Column(name="sex")
    private Integer sex;//乘客性别 0- 女 1- 男

    @Column(name="year")
    private Integer year;//乘客出生年份

    @Column(name="total_travel_mileage")
    private Integer totalMileage;//乘客总旅行里程

    @Column(name="total_travel_time")
    private Integer totalTime;//乘客总旅行时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(Integer totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", year=" + year +
                ", totalMileage=" + totalMileage +
                ", totalTime=" + totalTime +
                '}';
    }
}
