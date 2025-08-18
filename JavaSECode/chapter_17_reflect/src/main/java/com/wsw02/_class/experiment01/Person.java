package com.wsw02._class.experiment01;

import lombok.Data;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 父类 人
 */
@Data
public class Person {
    private String name;
    private int age;
    public double weight;

    public void displayInfo() {
        System.out.println("info:" + name + ":" + age + ":" + weight);
    }

    public void displayStuId() {}
}
