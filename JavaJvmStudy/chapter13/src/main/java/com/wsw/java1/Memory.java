package com.wsw.java1;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/5 17:27
 * @since 1.0
 */
public class Memory {
    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        Memory memory = new Memory();
        memory.foo(obj);
    }

    private void foo(Object param) {
        String str = param.toString();
        System.out.println(str);
    }
}
