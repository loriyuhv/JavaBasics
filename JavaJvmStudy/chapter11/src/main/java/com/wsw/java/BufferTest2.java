package com.wsw.java;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 本地内存OOM：java.lang.OutOfMemoryError: Direct buffer memory
 * -XX:MaxDirectMemorySize：默认与MaxHeapSize一致
 *
 * @author loriyuhv
 * @version 1.0 2025/10/3 16:35
 * @since 1.0
 */
public class BufferTest2 {
    private static final int BUFFER_SIZE = 1024 * 1024 * 20; // 20MB

    public static void main(String[] args) {
        ArrayList<ByteBuffer> list = new ArrayList<>();

        int count = 0;
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
                list.add(byteBuffer);
                count++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(count);
        }
    }
}
