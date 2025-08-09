package com.wsw01.oop;

/**
 * @author loriyuhv
 * @date 2025/8/9
 * @description 手机类
 */

public class Phone {
    // 属性
    String name;    // 品牌
    double price; // 价格

    // 方法
    public void call() {
        System.out.println("手机能够拨打电话");
    }

    public void sendMessage(String message) {
        System.out.println("发送信息，" + message);
    }

    public void playGame() {
        System.out.println("玩游戏！！！");
    }
}
