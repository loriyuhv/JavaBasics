package com.wsw99.review_study.exercise02;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public abstract class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public abstract void work();
    public abstract double calMoney(int days);
}
