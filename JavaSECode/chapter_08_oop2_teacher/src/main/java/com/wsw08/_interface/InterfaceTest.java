package com.wsw08._interface;
/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 接口
 */
public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(Flyable.MAX_SPEED);
        System.out.println(Flyable.MIN_SPEED);

        // Flyable.MAX_SPEED = 200; // 报错 final
        Bullet bullet = new Bullet();
        bullet.fly();
        bullet.attack();

        // 接口的多态性
        Flyable flyable = new Bullet();
        flyable.fly();
    }
}
