package com.wsw;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * 指令乱序：验证CPU指令重排导致的结果出错。
 *
 * @author loriyuhv
 * @version 1.0 2025/10/6 15:41
 * @since 1.0
 */
@JCStressTest
@Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "ok")
@Outcome(id = {"0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "!!!")
@State
public class ConcurrencyTest {
    int num = 0;
    volatile boolean ready = false;

    @Actor
    public void actor1(I_Result result) {
        if (ready) {
            result.r1 = num + num;
        } else {
            result.r1 = 1;
        }
    }

    @Actor
    public void actor2(I_Result result) {
        num = 2;
        ready = true;
    }

}
