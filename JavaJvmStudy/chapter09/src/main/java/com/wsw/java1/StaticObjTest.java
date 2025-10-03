package com.wsw.java1;

/**
 * 《深入理解Java虚拟机》中的案例：
 * staticObj、instanceObj、localObj存放在哪里？
 * staticObj存放在堆中；instanceObj存放在堆中；localObj存放在栈帧的局部变量表中。
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 21:36
 * @since 1.0
 */
public class StaticObjTest {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {

    }

    public static void main(String[] args) {

    }
}
