package com.wsw08._interface;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 功能：飞行
 */
public interface Flyable {
    // 全局常量
    public static final int MIN_SPEED = 0;
    // 可以省略public static final
    int MAX_SPEED = 100;

    // 方法可以省略public abstract
    void fly();
}
