package com.wsw.guarded_suspension.example03;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务相关类，收信人
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 6:42
 * @since 1.0
 */
@Slf4j
public class People extends Thread {
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        int id = guardedObject.getId();
        log.debug("开始收信 id: {}", id);
        String response = guardedObject.getResponse(3000);
        if (response != null) {
            log.debug("收到信了 id : {}，内容: {}", id, response);
        } else {
            log.debug("没收到信 id: {}", id);
        }
    }
}
