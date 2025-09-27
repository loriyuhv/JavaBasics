package com.wsw02.two;

/**
 * description 测试2
 *
 * @author loriyuhv
 * @since 1.0
 * @version  2025/8/15
 */
public class MemoryAnalysisOf2DArray2Test {
    public static void main(String[] args) {
        int[][] arr1 = new int[4][];
        arr1[0] = new int[3];
        arr1[1] = new int[]{1,2,3};
        arr1[0][2] = 5;
        arr1 = new int[2][];
        System.out.println(arr1[0]);
    }
}
