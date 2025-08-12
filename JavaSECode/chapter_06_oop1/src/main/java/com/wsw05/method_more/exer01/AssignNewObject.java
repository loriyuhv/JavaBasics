package com.wsw05.method_more.exer01;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description  练习
 */
public class AssignNewObject {
    public void swap(MyData my) {
        // System.out.println(my.toString());
        // my = new MyData();
        // System.out.println(my.toString());
        int temp = my.x;
        my.x = my.y;
        my.y = temp;
    }

    public static void main(String[] args) {
        AssignNewObject tools = new AssignNewObject();
        MyData data = new MyData();
        data.x = 1;
        data.y = 2;
        System.out.println("交换之前：x = " + data.x + ", y = " + data.y);
        tools.swap(data);
        System.out.println("交换之后：x = " + data.x + ", y = " + data.y);
    }
}
