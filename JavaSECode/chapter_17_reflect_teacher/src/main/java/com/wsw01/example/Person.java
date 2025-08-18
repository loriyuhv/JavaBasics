package com.wsw01.example;

import lombok.Data;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description Person
 */
@Data
public class Person {
    // 属性
    private String name;
    public int age;

    // 构造器
    public Person() {
        System.out.println("Person() ...");
    }

    public Person(int age) {
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 方法
    public void show() {
        System.out.println("你好！我是一个Person");
    }

    private String showNation(String nation) {
        return "我的国籍是" + nation;
    }
}
