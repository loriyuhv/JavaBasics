package com.wsw05.method_more;

import java.util.Arrays;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description n个字符串进行拼接，每一个字符串之间使用某字符进行分割，如果没有传入字符串，那么返回
 * 空字符串""
 */
public class StringTools {
    public String concat(char s, String... args) {
        String str = "";
        System.out.println("args:" + Arrays.toString(args));
        for (String arg : args) {
            if (arg != null) {
                str += s + arg;
            }
        }
        return str;
    }
}
