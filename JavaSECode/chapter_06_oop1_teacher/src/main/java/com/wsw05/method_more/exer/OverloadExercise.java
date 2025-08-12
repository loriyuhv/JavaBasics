package com.wsw05.method_more.exer;

/**
 * @author loriyuhv
 * @date 2025/8/10
 * @description 联系
 */
public class OverloadExercise {
    public static void main(String[] args) {
        OverloadExercise oe = new OverloadExercise();
        oe.mOL(2);
        oe.mOL(3, 4);
        oe.mOL("Hello World");

        System.out.println(oe.max(1.2, 2.3, 3.4));
    }

    /* 编写程序，定义三个重载方法并调用
    方法名为mOL。
    三个方法分别接收一个int参数、两个int参数、一个字符串参数。分别执行平方运算并输出结果，相
    乘并输出结果，输出字符串信息。
    在主类的main ()方法中分别用参数区别调用三个方法。*/
    public void mOL(int arg) {
        System.out.println(arg * arg);
    }

    public void mOL(int arg1, int arg2) {
        System.out.println(arg1 * arg2);
    }

    public void mOL(String string) {
        System.out.println(string);
    }

    /**
     * 定义三个重载方法max()，第一个方法求两个int值中的最大值，第二个方法求两个double值中的
     * 最大值，第三个方法求三个double值中的最大值，并分别调用三个方法。
     */
    public int max(int arg1, int arg2) {
        return Math.max(arg1, arg2);
    }

    public double max(double arg1, double arg2) {
        return Math.max(arg1, arg2);
    }

    public double max(double arg1, double arg2, double arg3) {
        return  arg1 > arg2 ? (Math.max(arg1, arg3)) : (Math.max(arg2, arg3));
    }
}
