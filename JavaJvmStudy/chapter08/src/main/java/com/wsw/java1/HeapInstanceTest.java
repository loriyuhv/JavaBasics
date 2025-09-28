package com.wsw.java1;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 14:13
 * @since 1.0
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            Thread.sleep(10);
        }
    }
}
