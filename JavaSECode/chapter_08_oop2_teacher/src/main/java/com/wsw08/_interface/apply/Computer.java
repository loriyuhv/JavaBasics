package com.wsw08._interface.apply;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description 计算机
 */
public class Computer {
    public void transferData(USB usb) {
        System.out.println("设备链接成功。。。");
        usb.start();
        System.out.println("数据传输的细节操作。。。");
        usb.stop();
    }
}
