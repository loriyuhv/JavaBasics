package com.wsw02.selfdefine;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description SubOrder3 是泛型类
 */
public class SubOrder3<T> extends Order<T> {
    /*T：继承自父类的泛型参数。由于父类的泛型参数类型不确定，子类也无法确定具体类型。*/
    public SubOrder3() {
    }

    public SubOrder3(T t, int orderId) {
        super(t, orderId);
    }

    public void show(T t) {
        System.out.println(t);
    }
}
