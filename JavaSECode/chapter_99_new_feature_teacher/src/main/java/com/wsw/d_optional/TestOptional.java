package com.wsw.d_optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * 为了在程序中避免出现空指针异常而创建的
 *
 * @author loriyuhv
 * @version 1.0 2026/8/16 13:51
 * @since 1.0
 */
public class TestOptional {
    /**
     * Optional.of(T t) 创建一个Optional实例，t必须非空
     * Optional.empty() 创建一个空的Optional实例
     * Optional.ofNullable(T t) t可以为null
     */
    @Test
    public void test01(){
        Girl girl = new Girl();
        // of(T t) 保证t必须非空
        Optional<Girl> optionalGirl = Optional.of(girl);
    }

    @Test
    public void test02(){
        Girl girl = new Girl();
        girl = null;
        // ofNullable(T t) t可以为空
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }

    @Test
    public void test03(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName01(boy);
        System.out.println(girlName);
    }

    public String getGirlName01(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void test04(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName02(boy);
        System.out.println(girlName);
    }

    // 优化
    public String getGirlName02(Boy boy){
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    @Test
    public void test05(){
        Boy boy = new Boy();
        // boy = null;
        boy = new Boy(new Girl("Lucy"));
        String girlName = getGirlName03(boy);
        System.out.println(girlName);
    }

    // 使用Optional类优化
    public String getGirlName03(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        // 此时boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("Alice")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        // 此时girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("Marry"));
        return girl1.getName();
    }
}
