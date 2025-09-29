package com.wsw.java;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 12:44
 * @since 1.0
 */
public class OOMTest extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest test = new OOMTest();
            for (int i = 0; i < 10_000; i++) {
                // 创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 指明版本号，修饰符，类名，报名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null );
                // 返回byte[]
                byte[] code = classWriter.toByteArray();
                // 类加载
                test.defineClass("Class" + i, code, 0, code.length); // Class对象
                j++;
            }
        } finally {
            System.out.println("j=" + j);
        }
    }
}
