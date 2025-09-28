package com.wsw.java1;

import java.util.ArrayList;

/**
 * 测试MinorGC、MajorGC、FullGC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 15:35
 * @since 1.0
 */
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            ArrayList<String> strings = new ArrayList<>();
            String name = "Jerry";
            while (true) {
                strings.add(name);
                name += name;
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }
}
