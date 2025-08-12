package com.wsw99.review_study.exercise01;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 管理者类
 */

public class Manager extends Employee {
    private double bonus;

    public Manager() {
    }

    public Manager(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String name, String id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    @Override
    void work() {
        System.out.println("我是管理者，我可以拿奖金工作");
    }
}
