package com.wsw.java2;

/**
 * @author loriyuhv
 * @date 2025/9/23 14:48
 * @description
 */
public class Son extends Father {
    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    // 不是重写父类的静态方法 因为静态方法不能被重写
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private " + str);
    }

    public void show() {
        showStatic("wsw.com");
        super.showStatic("good!");
        showPrivate("hello!");
        super.showCommon();
        showFinal();
        showCommon();
        info();
        MethodInterface in = null;
        in.methodA();
    }

    public void info() {

    }

    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

interface MethodInterface {
    void methodA();
}

class Father {
    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father show common");
    }
}
