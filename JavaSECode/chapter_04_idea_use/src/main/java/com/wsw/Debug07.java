package com.wsw;

/**
 * description 强制结束
 *
 * @author loriyuhv
 * @version 2025/9/26 13:45
 * @since 1.0
 */
public class Debug07 {
    public static void main(String[] args) {
        System.out.println("获取请求数据");
        System.out.println("调用写入数据库的方法");
        insert();
        System.out.println("程序结束");
    }

    private static void insert() {
        System.out.println("进入insert()方法");
        System.out.println("获取数据库连接");
        System.out.println("将数据写入数据库表中");
        System.out.println("写操作完成");
        System.out.println("断开连接");
    }
}
