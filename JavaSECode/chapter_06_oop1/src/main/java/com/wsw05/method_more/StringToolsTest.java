package com.wsw05.method_more;

import java.util.Arrays;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description StringTools测试类
 */
public class StringToolsTest {
    public static void main(String[] args) {
        StringTools stringTools = new StringTools();
        String string = stringTools.concat(',', "Hello", "World");
        System.out.println(string);
    }
}
