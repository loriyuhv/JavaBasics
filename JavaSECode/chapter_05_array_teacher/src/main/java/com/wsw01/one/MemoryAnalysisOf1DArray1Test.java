package com.wsw01.one;

/**
 * @author loriyuhv
 * @date 2025/8/15
 * @description 一维数组测试
 */
public class MemoryAnalysisOf1DArray1Test {
    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 5;
        arr[1] = 6;
        arr[2] = 7;

        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

        // 定义数组变量arr1，将arr的地址赋值给arr1
        int[] arr1 = arr;
        arr1[1] = 9;
        System.out.println(arr[1]);
    }
}
