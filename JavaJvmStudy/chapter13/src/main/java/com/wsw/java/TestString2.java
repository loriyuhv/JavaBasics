package com.wsw.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * -XX:StringTableSize=1009
 * JDK6:1009 JDK7及以后：60013
 * 注意：JDK8：设置的值必须在1009到2305843009213693951之间
 * 测试数据：1009: 78ms 60013: 26ms
 *
 * @author loriyuhv
 * @version 1.0 2025/10/5 16:00
 * @since 1.0
 */
public class TestString2 {
    public static void main(String[] args) throws InterruptedException {
        /* 测试StringTableSize参数 */
        System.out.println("我来打个酱油。");
        // Thread.sleep(1_000_000);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("words.txt"));
            long start = System.currentTimeMillis();
            String data;
            while ((data = br.readLine()) != null) {
                data.intern();
            }
            long end = System.currentTimeMillis();
            System.out.println("total time:" + (end - start));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
