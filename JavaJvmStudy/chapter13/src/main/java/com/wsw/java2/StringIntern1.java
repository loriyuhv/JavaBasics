package com.wsw.java2;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/11 21:07
 * @since 1.0
 */
public class StringIntern1 {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        String s2 = "11"; /* s2：字符串常量池中"11"的地址 */
        s1.intern(); /* s1：堆空间字符串对象"11"的地址 */
        /* s1.intern();详解：
        * 由于字符串常量池中存在"11"对象，不会再创建该对象，返回"11"对象的地址。
        *  */

        System.out.println(s1 == s2); /* false */
    }
}
