package com.wsw99.review_study.exercise01;

import lombok.Data;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 员工类
 */

public abstract class Employee {
    String name;
    String id;
    double salary;

    public Employee() {
    }

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    abstract void work();
}
