package com.wsw.a_lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Java内置的4大核心函数式接口
 * 消费型接口 Consumer<T> void accept(T t)
 * 供给型接口 Supplier<T> T get()
 * 函数型接口 Function<T, R> R apply(T t)
 * 断定型接口 Predicate<T> boolean test(T t)
 *
 * @author loriyuhv
 * @version 1.0 2026/8/13 20:14
 * @since 1.0
 */
public class TestLambda02 {
    /**
     * 消费型接口
     */
    @Test
    public void test01(){

        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double o) {
                System.out.println("学习太累了，去玩一玩，价格为：" + o);
            }
        });


        happyTime(3000, money -> System.out.println("今天花了" + money + "元。"));
    }

    public void happyTime(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }


    @Test
    public void test02(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Alan");
        list.add("Jerry");
        list.add("Marry");

        List<String> newList01 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("e");
            }
        });

        for (String s: newList01){
            System.out.println(s);
        }

        System.out.println("=============");

        List<String> newList02 = filterString(list, s -> s.contains("a"));
        for(String s: newList02){
            System.out.println(s);
        }
    }

    /**
     * 根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
     * @param list 未过滤的字符串列表
     * @param predicate 比较规则
     * @return 过滤后的字符层
     */
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(predicate.test(s)){
                filterList.add(s);
            }
        }

        return filterList;
    }
}
