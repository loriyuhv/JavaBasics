package com.wsw02.n3;

/**
 * @author loriyuhv
 * @version 2025/9/21 10:09
 * @since 1.0
 */
public class BFrames {
    public static void main(String[] args) {
        int a = 5;
        int b = 1;
        int z = a / b;
        method1(z);
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
