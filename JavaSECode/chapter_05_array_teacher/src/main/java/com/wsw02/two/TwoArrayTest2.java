package com.wsw02.two;

/**
 * @author loriyuhv
 * @date 2025/8/15
 * @description 数组元素的默认初始化值测试
 */
public class TwoArrayTest2 {
    public static void main(String[] args) {
        // 5. 数组元素的默认初始化值
        // 动态初始化方式1说明
        // 整数类型
        byte[][] bytes = new byte[2][2];
        System.out.println(bytes[0]);
        System.out.println(bytes[1]);
        System.out.println(bytes[0][0]);

        short[][] shortArrays = new short[2][2];
        System.out.println(shortArrays[0]);
        System.out.println(shortArrays[1]);
        System.out.println(shortArrays[0][0]);

        int[][] intArrays = new int[2][2];
        // 外层元素默认值
        System.out.println(intArrays[0]);
        System.out.println(intArrays[1]);
        // 内层元素默认值
        System.out.println(intArrays[0][0]);

        long[][] longArrays = new long[2][2];
        System.out.println(longArrays[0]);
        System.out.println(longArrays[1]);
        System.out.println(longArrays[0][0]);

        // 布尔型
        boolean[][] booleanArrays = new boolean[2][2];
        System.out.println(booleanArrays[0]);
        System.out.println(booleanArrays[1]);
        System.out.println(booleanArrays[0][0]);

        // 浮点型
        float[][] floatArrays = new float[2][2];
        System.out.println(floatArrays[0]);
        System.out.println(floatArrays[1]);
        System.out.println(floatArrays[0][0]);

        double[][] doubleArrays = new double[2][2];
        System.out.println(doubleArrays[0]);
        System.out.println(doubleArrays[1]);
        System.out.println(doubleArrays[0][0]);

        // 字符型
        char[][] charArrays = new char[2][2];
        System.out.println(charArrays[0]);
        System.out.println(charArrays[1]);
        System.out.println(charArrays[0][0]);

        System.out.println("-------------------------------");
        // 动态初始化方式2说明
        int[][] arrays = new int[2][];
        // 外层元素默认值
        System.out.println(arrays[0]);
        // 内层元素默认值
        System.out.println(arrays[0][0]);
    }
}
