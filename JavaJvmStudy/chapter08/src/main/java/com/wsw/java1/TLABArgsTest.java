package com.wsw.java1;

/**
 * 测试-XX:UseTLAB参数是否开启的情况：默认开启
 * cmd：jinfo -flag UseTLAB 进程号
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 16:09
 * @since 1.0
 */
public class TLABArgsTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello TLAB");
        Thread.sleep(1000_000);
    }
}
