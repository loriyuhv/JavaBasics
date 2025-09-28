package com.wsw.java;

/**
 * @author loriyuhv
 * @version 1.0 2025/9/28 11:12
 * @since 1.0
 */
public class SimpleHeap {
    private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {
        System.out.println("My ID is " + id);
    }

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);

        int[] arr = new int[10];
        Object[] objects = new Object[10];
    }
}
