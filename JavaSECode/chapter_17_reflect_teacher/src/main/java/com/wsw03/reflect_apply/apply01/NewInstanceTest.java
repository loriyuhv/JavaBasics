package com.wsw03.reflect_apply.apply01;

import com.wsw03.reflect_apply.data.Person;
import org.junit.jupiter.api.Test;

/**
 * description 反射的应用一：创建运行时类对象
 *
 * @author loriyuhv
 * @since 1.0
 * @version 2025/8/19
 */
public class NewInstanceTest {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        // 创建Person类的实例
        Person person = personClass.newInstance();
        System.out.println(person);
    }
}
