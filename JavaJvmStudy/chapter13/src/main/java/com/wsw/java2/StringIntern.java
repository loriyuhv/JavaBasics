package com.wsw.java2;

import org.junit.jupiter.api.Test;

/**
 * 如何保证变量s指向的是字符串常量池中的数据呢？
 * 有两种方式：
 * 方式一：String s = "Jerry"; 字面量定义的方式
 * 方式二：调用intern()
 *  String s = new String("Jerry").intern();
 *  String s = new StringBuilder("Jerry").toString().intern();
 * intern()方法作用：将字符串对象添加到字符串常量池（String pool）中，并返回常量池中该字符串的引用。
 *
 * @author loriyuhv
 * @version 1.0 2025/10/11 16:31
 * @since 1.0
 */
public class StringIntern {
    public static void main(String[] args) {
        // 第一部分：字面量创建 vs new创建
        String s1 = new String("1");
        /* 编译期：在字符串常量池中创建"1"；运行期：在堆空间创建字符串对象"1"，并把该对象的引用赋值给s1。 */

        s1.intern();
        /* 调用此方法前，字符常量池已经存在"1"，不会再创建"1"，返回"1"的引用。
           但返回值没有被接收，所以s1仍然指向堆中的对象。 */

        String s2 = "1";
        /* 把字符串常量池中的"1"引用赋值给s2。 */

        System.out.println(s1 == s2);
        /* JDK6/7/8: false
           原因：s1指向堆中的对象，s2指向常量池中的对象，始终是不同的对象 */

        System.out.println("============");

        // 第二部分：字符串拼接 + intern()
        String s3 = new String("1") + new String("1");
        /* s3变量记录的地址为：new String("11");
           执行过程：
           编译期：由于字符串常量池中已经存在"1"，不必再创建。
           运行期：new String("1"); new String("1"); StringBuilder s = new StringBuilder();
           s.append("1"); s.append("1");
           s.toString(); ==> new String("11"); 在堆空间中创建"11"，不会在字符串常量池中创建"11"。 */

        /* 执行完上一行代码之后，字符串常量池不存在"11"。 */

        s3.intern();
        /* 在字符串常量池中生成"11"的引用。
           JDK6：在永久代中创建了一个新的对象"11"，有新的地址。
           JDK7+：在堆中的常量池创建了一个指向s3对象的引用（即s3指向的堆对象）。*/

        String s4 = "11";
        /* s4变量记录的地址：使用的是上一行代码执行时，在常量池中生成的"11"的引用
           JDK6：指向常量池中新创建的"11"对象
           JDK7+：指向s3指向的堆对象 */

        System.out.println(s3 == s4);
        /* JDK6：false (s3指向堆对象，s4指向常量池新对象)
           JDK7/8: true (s3和s4都指向同一个堆对象) */
    }
}
