package com.wsw02.n3;

/**
 * @author loriyuhv
 * @date 2025/9/21 10:09
 * @description
 */
public class BFrames {
    public static void main(String[] args) {
        method1(10);
    }

    private static void method1(int x){
        int y = x + 1;
        Object o = method2();
        System.out.println(o + ":" + y);
    }

    private static Object method2(){
        return new Object();
    }
}
