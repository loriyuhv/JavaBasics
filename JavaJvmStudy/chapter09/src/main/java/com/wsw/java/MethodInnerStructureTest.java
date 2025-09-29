package com.wsw.java;

import java.io.Serializable;

/**
 * 测试方法区的内部构成
 * 查看方式一：jclasslib
 * 查看方式二：javap -v -p .\MethodInnerStructureTest.class > test.txt
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 14:09
 * @since 1.0
 */
public class MethodInnerStructureTest implements Comparable<String>, Serializable {
    public static void main(String[] args) {
        MethodInnerStructureTest test = new MethodInnerStructureTest();
        System.out.println("string: " + string + "num: " + test.num);
        test.test1();
        int i = test2(2);
        System.out.println("i: " + i);
    }
    /* 成员属性 public权限 */
    public int num = 10;
    /* 类属性 private权限*/
    private static final String string = "string";

    /* 构造器 默认空参构造器 */

    /* 成员方法 public权限 */
    public void test1() {
        int count = 20;
        System.out.println("count = " + count);
    }

    /* 类方法 public权限 */
    public static int test2(int cal) {
        int result = 0;
        try {
            int value = 30;
            result = value / cal;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return result;
    }
    @Override
    public int compareTo(String o) {
        return 0;
    }
}
