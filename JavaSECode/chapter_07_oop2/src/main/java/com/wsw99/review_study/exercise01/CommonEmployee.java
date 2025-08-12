package com.wsw99.review_study.exercise01;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class CommonEmployee extends Employee {
    public CommonEmployee() {
    }

    public CommonEmployee(String name, String id, double salary) {
        super(name, id, salary);
    }

    @Override
    void work() {
        System.out.println("我是普通员工，我没有奖金");
    }
}
