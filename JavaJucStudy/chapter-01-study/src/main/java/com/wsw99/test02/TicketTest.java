package com.wsw99.test02;

/**
 * @author loriyuhv
 * @date 2025/9/21 11:30
 * @description 卖票
 */
public class TicketTest {
    public static void main(String[] args) {
        Windows windows = new Windows();

        new Thread(()->{
            while(true) {
                boolean sell;
                try {
                    sell = windows.sell(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(!sell) {
                    break;
                }
            }
        }, "t1").start();

        new Thread(()->{
            while(true) {
                boolean sell;
                try {
                    sell = windows.sell(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(!sell) {
                    break;
                }
            }
        }, "t2").start();
    }
}
