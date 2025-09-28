package com.wsw.java1;

/**
 * 测试堆空间常用JVM参数：
 * -XX:+PrintFlagsInitial 查看所有参数的默认初始值
 * -XX:+PrintFlagsFinal 查看所有参数的最终值（可能会存在修改，不再是初始值）
 *  具体查看某个参数的指令：jps（查看当前运行中的进程） jinfo -flag SurvivorRatio 进程Id
 * -Xms：初始堆空间内存 （默认大小为 物理内存空间/64）
 * -Xmx：最大堆空间内存（默认大小为 物理内存空间/4）
 * -Xmn：设置新生代的大小（初始值和最大值）
 * -XX:NewRatio 配置新生代与老年代在堆结构的占比
 * -XX:SurvivorRatio 设置新生代中Eden和S0、S1空间的比例
 * -XX:MaxTenuringThreshold 设置新生代垃圾打最大年龄
 * -XX:+PrintGCDetails 输出详细的GC处理日志
 * 打印GC的简要信息
 *  1）-XX:PrintGC
 *  2）-verbose:gc
 * -XX:HandlePromotionFailure 是否设置空间分配担保
 *
 * @author loriyuhv
 * @version 1.0 2025/9/28 16:17
 * @since 1.0
 */
public class HeapArgsTest {
    public static void main(String[] args) {

    }
}
