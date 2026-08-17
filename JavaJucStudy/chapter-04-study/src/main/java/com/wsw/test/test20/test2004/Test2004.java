package com.wsw.test.test20.test2004;

import com.wsw.pattern.Downloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * @author loriyuhv
 * @version 1.0 2026/8/17 11:24
 * @since 1.0
 */
@Slf4j(topic = "Test2004")
public class Test2004 {

    public static void main(String[] args) {
        final GuardedObject<List<String>> go = new GuardedObject<>();

        new Thread(() -> {
            log.debug("等待结果 ");
            List<String> result = go.get();
            log.debug("结果大小：{}", result.size());
        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载 ");
            try {
                List<String> download = Downloader.download();
                go.complete(download);
            } catch (IOException e) {
               log.error(e.getMessage(), e);
            }
        }, "t2").start();

    }
}

@Slf4j(topic = "GuardedObject")
class GuardedObject<T> {
    private T result;

    public synchronized T get() {
        while (result == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
        return result;
    }

    public synchronized void complete(T result) {
        this.result = result;
        this.notify();
    }
}
