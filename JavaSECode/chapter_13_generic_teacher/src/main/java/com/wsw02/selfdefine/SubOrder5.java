package com.wsw02.selfdefine;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description SubOrder5是泛型
 */
public class SubOrder5<T, E> extends Order<T> {
    /*<T, E>解释：
    T：继承自父类的泛型参数。由于父类的泛型参数类型不确定，子类也无法确定其具体类型。
    E：子类独有的泛型参数，其类型同样不确定。
     */

    private E e;

    public SubOrder5() {
    }

    public SubOrder5(E e) {
        this.e = e;
    }

    public SubOrder5(T t, int orderId, E e) {
        super(t, orderId);
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
