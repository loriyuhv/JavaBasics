package com.wsw.java2;

/**
 *  面试题：new String("ab");会创建几个对象？
 *  回答：两个对象。证明：看字节码。
 *      对象1：new关键字在堆空间中创建的。
 *      对象2：字符串常量池中的对象。字节码指令：ldc
 *  拓展：new String("a") + new String("b");呢？
 *      对象1：new StringBuilder()
 *      对象2：new String("a")
 *      对象3：常量池中的"a"
 *      对象4：new String("b")
 *      对象5：常量池中的"b"
 *      深入剖析：StringBuilder的toString()
 *          对象6：new String("ab"); 强调：toString()的调用，在字符串常量池中，没有生成"ab"。
 *
 * @author loriyuhv
 * @version 1.0 2025/10/11 16:43
 * @since 1.0
 */
public class TestStringNew {
    public static void main(String[] args) {
        // String s = new String("ab");
        String s = new String("a") + new String("b");
    }
}
