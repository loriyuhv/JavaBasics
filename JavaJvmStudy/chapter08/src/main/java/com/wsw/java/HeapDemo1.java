package com.wsw.java;

/**
 * -Xms10m -Xmx10m
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 10:23
 * @since 1.0
 */
public class HeapDemo1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start...");
        Thread.sleep(1000_000);
        System.out.println("end...");
    }
}
