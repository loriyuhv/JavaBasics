package com.wsw04.test;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;

/**
 * @author loriyuhv
 * @date 2025/9/24 12:37
 * @description
 */
@Slf4j
public class Test01 {
    public static void main(String[] args) {
        Vector<Dog> dogs = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Dog dog = new Dog();
                dogs.add(dog);
                synchronized (dog) {
                    log.debug("\n{}\t{}", i, ClassLayout.parseInstance(dog).toPrintable());
                }
            }
            synchronized (dogs) {
                dogs.notify();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (dogs) {
                try {
                    dogs.wait();
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
            }
            log.debug("===============>");
            for (int i = 0; i < 30; i++) {
                Dog dog = dogs.get(i);
                log.debug("\n{}\t{}", i, ClassLayout.parseInstance(dog).toPrintable());
                synchronized (dog) {
                    log.debug("\n{}\t{}", i, ClassLayout.parseInstance(dog).toPrintable());
                }
                log.debug("\n{}\t{}", i, ClassLayout.parseInstance(dog).toPrintable());
            }
            log.debug("==============>");
            for (int i = 0; i < 30; i++) {
                log.debug("\n{}\t{}", i, ClassLayout.parseInstance(dogs.get(i)).toPrintable());
            }
        }, "t2");
        t2.start();
    }
}

class Dog {

}
