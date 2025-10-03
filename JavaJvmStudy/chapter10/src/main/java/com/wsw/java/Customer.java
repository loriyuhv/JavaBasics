package com.wsw.java;

/**
 * 测试对象实例化的过程：
 * 1）加载类元信息 2）为对象分配内存 3）处理并发安全问题 4）属性的默认初始化
 * 5）设置对象头的信息 6）属性的显式初始化、代码块初始化和构造器初始化
 * 对象属性的赋值操作：
 * 1）默认初始化 ==> 2）显式初始化 ==> 3）代码块初始化 ==> 4）构造器初始化
 *
 * @author loriyuhv
 * @version 1.0 2025/10/3 14:24
 * @since 1.0
 */
public class Customer {
    int id = 1001;
    String name;
    Account account;

    {
        name = "John Smith";
    }

    public Customer() {
        account = new Account();
    }
}

class Account {}
