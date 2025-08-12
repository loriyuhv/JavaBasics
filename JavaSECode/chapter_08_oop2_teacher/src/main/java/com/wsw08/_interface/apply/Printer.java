package com.wsw08._interface.apply;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class Printer implements USB {
    @Override
    public void start() {
        System.out.println("打印机开始工作。。。");
    }

    @Override
    public void stop() {
        System.out.println("打印机结束工作。。。");
    }
}
