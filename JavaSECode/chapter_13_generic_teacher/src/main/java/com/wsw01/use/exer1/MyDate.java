package com.wsw01.use.exer1;

import lombok.Getter;
import lombok.Setter;

/**
 * @author loriyuhv
 * @date 2025/8/17
 * @description MyDate类包含：
 *     private成员变量year，month，day；并为每一个属性定义getter，setter方法；
 */
@Setter
@Getter
public class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }

    @Override
    public int compareTo(MyDate o) {
        System.out.println(this.month + ":" + o.month);

        int yearDistance = this.year - o.year;
        if (yearDistance != 0) {
            return yearDistance;
        }
        int monthDistance = this.month - o.month;
        if (monthDistance != 0) {
            return monthDistance;
        }
        return this.day - o.day;
    }
}
