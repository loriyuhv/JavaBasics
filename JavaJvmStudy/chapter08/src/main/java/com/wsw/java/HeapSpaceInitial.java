package com.wsw.java;

/**
 * 1. 设置堆空间大小的参数
 * -Xms 用来设置堆空间（年轻代+老年代）初始内存大小；-XX:InitialHeapSize
 *  -X 是JVM的运行参数
 *  ms 是memory start
 * -Xmx 用来设置堆空间（年轻代+老年代）最大内存大小；-XX:MaxHeapSize
 * 2. 默认堆空间的大小
 *  初始内存大小：物理电脑内存 / 64
 *  最大内存大小：物理电脑内存 / 4
 * 3. 手动设置 -Xms600m -Xmx600m
 *  开发中建议将初始堆内存和最大堆内存设置同相同的值。避免系统不断去调整内存大小（扩容或缩容）。
 * 4. 查看设置的参数
 *  方式一：jps（查看进程信息，获取对应的进程号）jstat -gc 进程号
 *  方式二：-XX:+PrintGCDetails
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 12:02
 * @since 1.0
 */
public class HeapSpaceInitial {
    public static void main(String[] args) throws InterruptedException {
        // 返回Java虚拟机中堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回Java虚拟机中试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

        // System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
        // System.out.println("系统内存大小为：" + maxMemory * 64.0 / 1024 + "G");
        // Thread.sleep(1000_000);
    }
}
