package com.wsw02.selfdefine;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description SubOrder4是泛型类
 */
public class SubOrder4<E> extends Order<Integer> {
    E t;

    public SubOrder4() {
    }

    public SubOrder4(Integer integer, int orderId) {
        super(integer, orderId);
    }

    public E getE() {
        return t;
    }

    public void setE(E t) {
        this.t = t;
    }
}
