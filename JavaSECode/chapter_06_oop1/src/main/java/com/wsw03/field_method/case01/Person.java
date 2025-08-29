package com.wsw03.field_method.case01;

/**
 * @author loriyuhv
 * @date 2025/8/29 10:01
 * @description
 */
public class Person {
    String name;
    int age;
    String gender;

    public void eat() {
        System.out.println("人需要吃饭。");
    }

    public void sleep(int hour) {
        System.out.println("人需要每天保证" + hour + "小时的睡眠时间。");
    }

    public String interests(String hobby) {
        String info = "人的兴趣爱好是" + hobby;
        System.out.println(info);
        return info;
    }
}
