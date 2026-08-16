package com.wsw.test.test20.test2001;


import com.wsw.pattern.Downloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * // 线程1等待线程2的下载结果
 * @author loriyuhv
 * @version 1.0 2026/8/16 15:31
 * @since 1.0
 */
@Slf4j(topic = "c.Test2001")
public class Test2001 {
    private static GuardedObject guardedObject = new GuardedObject();

    public static void main(String[] args) {
        new Thread(() -> {
            log.debug("等待结果");
            List<String> response = (List<String>) guardedObject.getResponse();
            log.debug("结果大小：{}", response.size());

        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载");
            try {
                List<String> list = Downloader.download();
                guardedObject.complete(list);
            } catch (IOException e) {
               log.error(e.getMessage(), e);
            }
        }, "t2").start();
    }


}


// 增加超时效果
@Slf4j(topic = "c.GuardedObject")
class GuardedObject {
    // 结果
    private Object response;

    /**
     * 获取结果
     * @return 结果
     */
    public synchronized Object getResponse() {
            // 没有结果
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        return response;
    }

    /**
     * 产生结果
     * @param response 结果
     */
    public synchronized void complete(Object response) {
        // 给结果成员变量赋值
        this.response = response;
        this.notify();
    }
}
