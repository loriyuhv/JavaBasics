package com.wsw02.selfdefine;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description 自定义泛型类
 */
public class Order<T> {
    // 声明了类的的泛型参数以后，就可以在类的内部使用泛型参数
    T t;
    int orderId;

    public Order() {
    }

    public Order(T t, int orderId) {
        this.t = t;
        this.orderId = orderId;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "t=" + t +
                ", orderId=" + orderId +
                '}';
    }

    // 自定义泛型方法
    public <E> void method(E e) {
    }

    // 定义泛型方法，将E[]数组元素添加到对应类型的ArrayList当中，并返回
    public <E> ArrayList<E> copyFromArrayToList (E[] arr) {
        // ArrayList<E> list = new ArrayList<>();
        // for (int i = 0; i < arr.length; i++) {
        //     list.add(arr[i]);
        // }
        // list.addAll(Arrays.asList(arr)); // 简化for循环的方式添加
        // return list;

        return new ArrayList<>(Arrays.asList(arr));
    }
}
