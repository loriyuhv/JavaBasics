package com.wsw.guarded_suspension.example03;

/**
 * @author loriyuhv
 * @version 1.0 2025/9/29 6:57
 * @since 1.0
 */
public class BusinessTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }

        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            new Postman(i + 1, "hello" + i).start();
        }
    }
}
