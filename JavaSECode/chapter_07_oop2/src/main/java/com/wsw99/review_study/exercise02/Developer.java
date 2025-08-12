package com.wsw99.review_study.exercise02;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 开发者类
 */
public class Developer extends Employee {
    public Developer(String name, int age) {
        super(name, age);
    }

    @Override
    public void work() {
        System.out.println("我主要负责软件开发。");
    }

    @Override
    public double calMoney(int days) {
        return days > 60 ? days * 400 : days * 500;
    }
}
