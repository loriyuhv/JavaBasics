package com.wsw01.oop;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description Phone类的测试类
 */
public class PhoneTest {
    public static void main(String[] args) {
        // 创建Phone的对象
        Phone phone = new Phone();

        // 通过Phone的对象，调用其内部声明的属性或方法
        // 格式：“对象.属性” 或“对象.方法”
        phone.name = "XIAOMI";
        phone.price = 4599;

        System.out.println("name: " + phone.name + "\nprice: " + phone.price);
        // 调用方法
        phone.call();
        phone.sendMessage("Hello phone!");
    }
}
