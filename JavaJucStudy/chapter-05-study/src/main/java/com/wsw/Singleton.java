package com.wsw;

/**
 * @author loriyuhv
 * @version 1.0 2025/10/13 14:31
 * @since 1.0
 */
public final class Singleton {
    private Singleton() {}

    private static Singleton instance = null;
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
