package com.wsw05.method_more._01overload;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description 方法重载
 */
public class OverloadTest {
    public void add(int i, int j) {}

    public void add(String s1, String s2) {}

    public void add(int i, String s) {}

    // 方法名相同且形参列表相同（类型、顺序和个数完全相同）
    // public void add(int num, int average) {}


    // public int add(int i, int j) {
    //     return i+j;
    // }

    public int add(int a, double b) {
        return 1;
    }
}
