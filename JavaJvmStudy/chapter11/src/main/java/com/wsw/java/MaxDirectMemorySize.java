package com.wsw.java;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -XX:MaxDirectMemorySize：默认与MaxHeapSize一致
 *
 * @author loriyuhv
 * @version 1.0 2025/10/3 16:52
 * @since 1.0
 */
public class MaxDirectMemorySize {
    private static final long _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredFields()[0];
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
