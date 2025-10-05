package com.wsw.java;

import java.util.HashSet;
import java.util.Set;

/**
 * JDK8：-XX:InitialHeapSize=6m -XX:MaxHeapSize=6m -XX:MetaspaceSize=6m -XX:MaxMetaspaceSize=6m
 * @author loriyuhv
 * @version 1.0 2025/10/5 16:57
 * @since 1.0
 */
public class TestString3 {
    public static void main(String[] args) {
        /* 使用Set保持常量池引用，避免full gc回收常量池行为 */
        Set<String> set = new HashSet<String>();
        /* 在short可以取值的范围内足以让6MB的PermSize或Heap产生OOM了 */
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
