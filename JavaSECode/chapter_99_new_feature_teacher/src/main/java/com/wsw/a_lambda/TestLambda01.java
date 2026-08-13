package com.wsw.a_lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式使用
 * <p>1. 举例：(o1, o2) -> Integer.compare(o1, o2);</p>
 * <p>2. 格式</p>
 *  ->：Lambda操作符
 *  ->左边：Lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *  ->右边：lambda体（其实就是重写的抽象方法的方法体）
 * <p>3. Lambda表达式使用：六种情况</p>
 *  <ul>
 *      <li>语法格式1：无参数、无返回值</li>
 *      <li>语法格式2：Lambda需要一个参数、无返回值</li>
 *      <li>语法格式3：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”</li>
 *      <li>语法格式4：Lambda若只需要一个参数时，参数的小括号可以省略</li>
 *      <li>语法格式5：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值</li>
 *      <li>语法格式6：当Lambda体只有一条语句是，return与大括号若有，都可以省略</li>
 *  </ul>
 * <p>4. Lambda表达式的本质：作为函数式接口的实例</p>
 * <p>5. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口</p>
 *
 * @author loriyuhv
 * @version 1.0 2026/3/22 10:19
 * @since 1.0
 */
public class TestLambda01 {
    @Test
    public void test() {
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        int compare1 = comparator1.compare(3, 2);
        System.out.println(compare1);

        /* Lambda写法 */
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);


        int compare2 = comparator2.compare(2, 3);
        System.out.println(compare2);

        /* 方法引用 */
        Comparator<Integer> comparator3 = Integer::compare;

        int compare3 = comparator3.compare(2, 3);
        System.out.println(compare2);
    }

    /* 语法格式1：无参数、无返回值 */
    @Test
    public void test01() {
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("Hello Lambda1");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("Hello Lambda2");
        r2.run();
    }

    /* 语法格式2：Lambda需要一个参数、无返回值 */
    @Test
    public void test3() {
        Consumer<String> c1 = new Consumer<String>() {
            public void accept(String s) {
                System.out.println(s);
            }
        };
        c1.accept("Hello Lambda1");

        Consumer<String> c2 = (String s) -> System.out.println(s);
        c2.accept("Hello Lambda2");

        Consumer<String> c3 = System.out::println;
        c3.accept("Hello Lambda3");
    }

    /* 语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断” */
    @Test
    public void test03() {
        Consumer<String> c1 = (String s) -> System.out.println(s);
        c1.accept("Hello Lambda1");

        Consumer<String> c2 = (s) -> System.out.println(s);
        c2.accept("Hello Lambda2");
    }

    /* 语法格式四：Lambda若只需要一个参数时，参数的小括号可以省略 */
    @Test
    public void test04() {
        Consumer<String> c1 = (s) -> System.out.println(s);
        c1.accept("Hello Lambda1");

        Consumer<String> c2 = s -> System.out.println(s);
        c2.accept("Hello Lambda2");
    }

    /* 语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值 */
    @Test
    public void test05() {
        Comparator<Integer> c1 = new Comparator<>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = c1.compare(3, 2);
        System.out.println(compare1);

        Comparator<Integer> c2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = c2.compare(1, 2);
        System.out.println(compare2);

        Comparator<Integer> c3 = Integer::compare;
        int compare3 = c3.compare(1, 1);
        System.out.println(compare3);
    }

    /* 语法格式六：当Lambda体只有一条语句是，return与大括号若有，都可以省略 */
    @Test
    public void test06() {
        Comparator<Double> c1 = (o1, o2) -> Double.compare(o1, o2);
        double compare1 = c1.compare(2.0, 3.0);
        System.out.println(compare1);

        Comparator<Double> c2 = Double::compare;
        double compare2 = c2.compare(2.0, 3.0);
        System.out.println(compare2);
    }

    @Test
    public void test07() {
        MyInterface<String> i = (s) -> System.out.println(s);
        i.method("Hello Lambda");

        MyInterface<Integer> j = System.out::println;
        j.method(3);
    }
}
