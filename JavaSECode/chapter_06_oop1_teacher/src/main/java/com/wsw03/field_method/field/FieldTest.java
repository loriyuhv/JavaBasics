package com.wsw03.field_method.field;

/**
 * @author loriyuhv
 * @date 2025/8/28 8:25
 * @description
 */
public class FieldTest {
    // 属性（成员变量）
    String name;
    int age;

    // 方法
    public void eat() {
        String food = "红烧排骨"; // food：局部变量
        System.out.println("我喜欢吃" + food + "。");
    }

    public void sleep(int hour) { // 形参：局部变量
        System.out.println("人不能少于" + hour + "小时睡眠。");
    }
}
