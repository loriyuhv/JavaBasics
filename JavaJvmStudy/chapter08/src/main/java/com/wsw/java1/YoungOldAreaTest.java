package com.wsw.java1;

/**
 * 测试：大对象直接进入老年代
 * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * YoungGeneration: 20m OldGeneration: 40m
 * Eden Space: 16m Survivor0: 2m Survivor1: 2m
 * @author loriyuhv
 * @version 1.0 2025/9/28 15:56
 * @since 1.0
 */
public class YoungOldAreaTest {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024 * 1024 * 20];// 20MB
    }
}
