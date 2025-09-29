package com.wsw.java;

/**
 * 非final的类变量：其初始化在类加载阶段（运行期）完成。
 * final类变量（编译期常量）：其值在编译后就被直接写入字节码，使用该常量的地方会被替换为字面量。
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 17:00
 * @since 1.0
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
    }
}

class Order {
    public static int count = 1;
    public static final int number = 2;

    public static void hello() {
        System.out.println("hello!");
    }
}
