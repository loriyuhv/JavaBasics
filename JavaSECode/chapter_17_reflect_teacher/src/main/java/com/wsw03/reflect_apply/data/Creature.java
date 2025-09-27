package com.wsw03.reflect_apply.data;

/**
 * description Creature
 *
 * @author loriyuhv
 * @version 2025/8/19
 */
public class Creature<T> {
    boolean gender;
    public int id;

    public void breath() {
        System.out.println("呼吸！");
    }

    private void info() {
        System.out.println("我是一个生物。");
    }
}
