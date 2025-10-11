package com.wsw.java1;

import org.junit.jupiter.api.Test;

/**
 * 字符串拼接操作
 *
 * @author loriyuhv
 * @version 1.0 2025/10/10 16:26
 * @since 1.0
 */
public class TestString5  {

    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";
        String s2 = "abc";
        /*
        * 最终，java编译成.class，再执行.class
        * String s1 = "abc";
        * String s2 = "abc";
         */

        System.out.println(s1 == s2); // true
        System.out.println(s1.equals(s2)); // true
    }

    @Test
    public void test2() {
        String s1 = "JavaEE";
        String s2 = "Hadoop";

        String s3 = "JavaEEHadoop";
        String s4 = "JavaEE" + "Hadoop";
        /* 如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容
        * 为拼接的结果，JavaEEHadoop */
        String s5 = s1 + "Hadoop";
        String s6 = "JavaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s6 == s7); // false
        
        /* intern(); 判断字符串常量池中是否存在JavaEEHadoop值，如果存在，则返回常量池中
        * JavaEEHadoop的地址；如果字符串常量池中不存在JavaEEHadoop,则在常量池中加载一份
        * JavaEEHadoop，并返回对象的地址。 */
        String s8 = s6.intern();
        System.out.println(s3 == s8); // true
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        /* s1 + s2 代码的执行细节
        * 1）StringBuilder s = new StringBuilder();
        * 2）s.append("a);
        * 3）s.append("b);
        * 4）s.toString(); ==> 约等于 new String(); */
        System.out.println(s3 == s4); // false
    }

    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4); // true
        /*
        * 1.字符串拼接操作不一定使用的是StringBuilder！
        * 如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化。
        * 2. 针对于final修饰类、方法、基本数据类型、引用数据类型的结构时，能使用
        * 上final的时候建议使用上。*/
    }

    @Test
    public void test5() {
        String s1 = "JavaEEHadoop";
        String s2 = "JavaEE";
        String s3 = s2 + "Hadoop";
        System.out.println(s1 == s3); // false

        final String s4 = "JavaEE";
        String s5 = s4 + "Hadoop";
        System.out.println(s1 == s5); // true
    }

    /**
     * 体会执行效率：通过StringBuilder的append()方式添加字符串的效率远高于String的字符串拼接方式！
     * 好处：
     *  1）
     *      StringBuilder的append()的方式：自始自终只创建过一个StringBuilder的对象。
     *      String字符串拼接方式：创建过多个StringBuilder和String对象。
     *  2）内存中创建了较多的StringBuilder和String对象，内存占用更大，需要花费额外的时间进行GC。
     * 改进：在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况下，建议
     * 使用构造器StringBuilder(int capacity)。
     *
     */
    @Test
    public void test6() {
        long start = System.currentTimeMillis();
        // method1(100000); // 3042ms
        method2(100000); // 5ms 改进之后 2ms
        long end = System.currentTimeMillis();
        System.out.println("total time: " + (end - start));
    }

    public void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            src = src + "a"; // 每次循环都会创建一个StringBuilder、String
        }
    }

    public void method2(int highLevel) {
        /* 只需要创建一个StringBuilder */
        StringBuilder src = new StringBuilder(highLevel);
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }

}
