package com.wsw99.review_study.exercise02;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class Manager extends Employee {
    public Manager(String name, int age) {
        super(name, age);
    }

    @Override
    public void work() {
        System.out.println("我的工作是管理开发人员。");
    }

    @Override
    public double calMoney(int days) {
        return days > 60 ? days * 700 : days * 800;
    }
}
