package com.wsw04.test02;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * JDK15开始，废弃偏向锁
 * JDK15锁状态：无锁01 ==> 轻量级锁（自旋锁）00 ==> 重量锁10
 *
 * @author loriyuhv
 * @version 1.0 2025/9/24 13:08
 */
@Slf4j
public class Test01 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        log.debug("\n{}", ClassLayout.parseInstance(cat).toPrintable());
        synchronized (cat) {
            log.debug("\n{}", ClassLayout.parseInstance(cat).toPrintable());
        }
        log.debug("\n{}", ClassLayout.parseInstance(cat).toPrintable());
    }
}

class Cat {

}
