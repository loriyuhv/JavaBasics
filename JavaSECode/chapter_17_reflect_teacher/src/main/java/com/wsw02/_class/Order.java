package com.wsw02._class;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description
 */
public class Order {
    // static int orderDoc;
    // 只要有一个static修饰的变量显示赋值，就会有clinit方法
    static int orderDoc = 1;
    static {
        orderDoc = 2;
    }
}
