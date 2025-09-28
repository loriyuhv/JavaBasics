package com.wsw.java1;

/**
 * -Xms600m -Xmx600m
 * -XX:NewRatio：设置年轻代与老年代比例，默认值是2，也就是1:2。
 * -XX:SurvivorRatio：设置幸存者区与伊甸园代比例，默认值是8，也就是1:1:8。
 *  注意：但实际不是，而是1:1:6
 * -XX:-UseAdaptiveSizePolicy：关闭自适应的内存分配策略（暂时用不到）
 * -XX:SurvivorRatio=8：显式赋值才起作用。
 * -Xmn：设置新生代的空间大小。（一般不设置）
 *
 * @author loriyuhv
 * @since 1.0
 * @version 1.0 2025/9/28 13:24
 */
public class EdenSurvivorTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello EdenSurvivor");
        Thread.sleep(1000_000);
    }
}
