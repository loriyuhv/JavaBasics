package com.wsw02._class;

import lombok.Data;

/**
 * description User
 *
 * @author loriyuhv
 * @version 2025/8/18
 */
@Data
public class User {
    // 属性
    private String name;
    public int age;


    // 构造器
    public User() {
        // System.out.println("User() ...");
    }

    public User(int age) {
        this.age = age;
    }

    public User(String name, int age) {}

    // 方法
}
