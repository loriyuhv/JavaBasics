package com.wsw.guarded_suspension.example03;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务相关类：邮递员
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 6:53
 * @since 1.0
 */
@Slf4j
@AllArgsConstructor
public class Postman extends Thread {
    private int id;
    private String response;

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        log.debug("送信 id : {}，内容 : {}", id, response);
        guardedObject.setResponse(response);
    }
}
