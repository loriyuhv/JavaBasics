package com.wsw08._interface;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 子弹
 */
public class Bullet implements Flyable, Attack {

    @Override
    public void attack() {
        System.out.println("Bullet attacked");
    }

    @Override
    public void fly() {
        System.out.println("Bullet flying");
    }
}
