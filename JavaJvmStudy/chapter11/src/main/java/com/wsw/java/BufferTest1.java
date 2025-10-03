package com.wsw.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * IO VS NIO
 *
 * @author loriyuhv
 * @version 1.0 2025/10/3 15:54
 * @since 1.0
 */
public class BufferTest1 {
    private static final String TO = "E:\\a.flv";
    private static final int _100Mb = 1024 * 1024 * 100;

    public static void main(String[] args) {
        long sum = 0;
        String src = TO;

        for (int i = 0; i < 3; i++) {
            String dest = "E:\\a_" + i + ".flv";
            sum += io(src, dest); // 2710
            sum += directBuffer(src, dest); // 1769
        }

        System.out.println("total time: " + sum);
    }

    private static long directBuffer(String src, String dest) {
        long start = System.currentTimeMillis();

        try (
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest)
        ) {
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100Mb);
            while(inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip(); // 修改为读数据模式
                outChannel.write(byteBuffer);
                byteBuffer.clear(); // 清空
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return System.currentTimeMillis() - start;
    }

    private static long io(String src, String dest) {
        long start = System.currentTimeMillis();

        try (
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest)
        ) {
            byte[] buffer = new byte[_100Mb];
            while (true) {
                int len = fis.read(buffer);
                if (len == -1) {
                    break;
                }
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return System.currentTimeMillis() - start;
    }
}
