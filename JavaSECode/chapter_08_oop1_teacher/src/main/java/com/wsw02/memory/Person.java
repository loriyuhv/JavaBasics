package com.wsw02.memory;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description Person类
 */
public class Person {
    // 属性
    String name;
    int age;
    char gender;

    // 方法
    public void eat() {
        System.out.println("人吃饭！");
    }

    public void sleep(int hour) {
        System.out.println("人至少保证每天" + hour + "小时睡眠!");
    }
}
