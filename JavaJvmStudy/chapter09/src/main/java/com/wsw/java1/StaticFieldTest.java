package com.wsw.java1;

/**
 * 结论：静态引用对应的对象实体始终都存储在堆中。
 * JDK7:
 * -Xms200m -Xmx200m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails
 * JDK8:
 * -Xms200m -Xmx200m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 21:22
 * @since 1.0
 */
public class StaticFieldTest {
    private static byte[] arr = new byte[1024 * 1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        System.out.println(arr);

        // Thread.sleep(1_000_000);
    }
}
