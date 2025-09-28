package com.wsw.java2;

/**
 * 逃逸分析
 * 如何快速判断是否发生了逃逸：大家就看new对象实体是否有可能在方法外被调用。
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 16:51
 * @since 1.0
 */
public class EscapeAnalysis {
    public EscapeAnalysis obj;

    /* 方法返回EscapeAnalysis对象，发生逃逸 */
    public EscapeAnalysis getInstance() {
        return obj == null ? new EscapeAnalysis() : obj;
    }

    /* 为成员属性赋值，发生逃逸 */
    // 思考：如果当前的obj引用声明为static的变量，还会发生逃逸吗？仍然会发生逃逸。
    public void setObj() {
        this.obj = new EscapeAnalysis();
    }

    /* 对象的作用域仅在当前方法中有效，不会发生逃逸 */
    public void useEscapeAnalysis() {
        EscapeAnalysis e = new EscapeAnalysis();
    }

    /* 引用成员变量的值，发生逃逸 */
    public void useEscapeAnalysis1() {
        EscapeAnalysis e = this.getInstance();
        // getInstance().xxx(); // 同样会发生逃逸
    }
}