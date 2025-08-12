package com.wsw08._interface.apply;

/**
 * @author loriyuhv
 * @date 2025/8/12
 * @description TODO
 */
public class USBTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        // 1. 创建接口实现类的对象
        Printer printer = new Printer();
        computer.transferData(printer);
        // 2. 创建接口实现类的匿名对象
        computer.transferData(new Camera());

        // 3. 创建接口匿名实现类的对象
        USB usb = new USB() {
            @Override
            public void start() {
                System.out.println("USB start");
            }

            @Override
            public void stop() {
                System.out.println("USB stop");
            }
        };
        computer.transferData(usb);

        // 4. 创建接口匿名实现类的匿名对象
        computer.transferData(new USB() {
            @Override
            public void start() {
                System.out.println("USB start");
            }

            @Override
            public void stop() {
                System.out.println("USB stop");
            }
        });

    }
}
