package com.wsw.java;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * IO                              NIO（New IO/Non-Blocking IO）
 * byte[] /char[]字节流或字符流      Buffer
 * Stream                          Channel
 * 查看直接内存的占用与释放。
 *
 * @author loriyuhv
 * @version 1.0 2025/10/3 15:19
 * @since 1.0
 */
public class BufferTest {
    private static final int BUFFER = (int) ((1024L * 1024 * 1024 * 2) - 1); // 2GB

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("直接内存分配完毕，请求指示！");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放！");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
