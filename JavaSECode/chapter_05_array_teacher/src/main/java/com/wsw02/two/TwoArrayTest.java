package com.wsw02.two;

import java.util.Arrays;

/**
 * @author loriyuhv
 * @date 2025/8/15
 * @description 二维数组的使用
 */
public class TwoArrayTest {
    public static void main(String[] args) {
        // 1. 数组的声明与初始化
        // 复习
        // int[] arr1 = new int[]{1,2,3}; // 静态初始化
        // int[] arr2 = new int[5]; // 动态初始化
        // arr2[0] = 1;
        // arr2[1] = 2;
        // arr2[2] = 3;

        // 方式1：静态初始化：数组变量的赋值和数组元素的赋值同时进行
        // int[][] arr1 = new int[][]{{1,2}, {3,4}, {5,6},{7,8,9}};

        // 方式2：动态初始化2：数组变量的赋值和数组元素的赋值分开进行
        // String[][] arr2 = new String[3][2];
        // 方式2：动态初始化2
        // double[][] arr3 = new double[3][];

        // 其他正确的写法
        // int arr4[][] = new int[][]{{1,2},{3,4}};
        // int[] arr5[] = new int[][]{{1,2},{3,4}};
        // int[][] arr6 = {{1,2},{3,4}}; // 类型推断

        // 错误的写法
        // int[][] arr7 = new int[3][3]{{1,2}, {3,4}, {5,6},{7,8,9}};
        // int[3][3] arr8 = new int[][]{{1,2}, {3,4}, {5,6},{7,8,9}};


        // 2. 数组的调用
        // int[][] arr1 = new int[][]{{1,2}, {3,4}, {5,6},{7,8,9}};
        // String[][] arr2 = new String[3][2];
        // double[][] arr3 = new double[3][];
        // 调用内层元素
        // System.out.println(arr1[0][0]);
        // System.out.println(arr1[2][1]);
        // System.out.println(arr1[2]);
        // 测试arr2,arr3
        // arr2[0][1] = "Tom";
        // System.out.println(arr2[0][1]);
        // arr3[0] = new double[4];
        // arr3[0][0] = 1D;
        // System.out.println(arr3[0][0]);

        // 3. 数组的长度
        int[][] arr1 = new int[][]{{1,2}, {3,4}, {5,6},{7,8,9}};
        System.out.println(arr1.length);
        System.out.println(arr1[0].length);
        System.out.println(arr1[3].length);

        // 4. 如何遍历数组
        // 方式一
        // for (int i = 0; i < arr1.length; i++) {
        //     for (int j = 0; j < arr1[i].length; j++) {
        //         System.out.print(arr1[i][j]);
        //     }
        //     System.out.println();
        // }

        // 方式二
        for (int[] ints : arr1) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
