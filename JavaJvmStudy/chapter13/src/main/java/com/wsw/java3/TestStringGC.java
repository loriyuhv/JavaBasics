package com.wsw.java3;

/**
 * String的垃圾回收:
 * -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 *
 * @author loriyuhv
 * @version 1.0 2025/10/12 13:26
 * @since 1.0
 */
public class TestStringGC {
    public static void main(String[] args) {
        for (int j = 0; j < 100000; j++) {
            String.valueOf(j).intern();
        }
    }
}
