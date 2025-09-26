package com.wsw;

/**
 * description
 *
 * @author loriyuhv
 * @version 2025/9/26 12:14
 * @since 1.0
 */
public class Debug01 {
    public static void main(String[] args) {
        int m = 10;
        int n = 20;

        System.out.println("m = " + m + ", n = " + n);
        swap(m, n);
        System.out.println("m = " + m + ", n = " + n);

        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        System.out.println(arr);

        char[] arr1 = new char[] { 'a', 'b', 'c' };
        System.out.println(arr1);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}
