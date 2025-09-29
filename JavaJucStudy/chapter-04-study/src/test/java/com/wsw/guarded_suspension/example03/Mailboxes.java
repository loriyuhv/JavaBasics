package com.wsw.guarded_suspension.example03;

import java.util.Hashtable;
import java.util.Map;
// import java.util.Set;

/**
 * 中间解耦类
 *
 * @author loriyuhv
 * @version 1.0 2025/9/29 6:32
 * @since 1.0
 */
public class Mailboxes {
    private static final Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;

    /**
     * 产生唯一id
     * @return id
     */
    private synchronized static int generateId() {
        return id++;
    }

    /**
     * 创建结果类
     * @return 结果对象
     */
    public static GuardedObject createGuardedObject() {
        GuardedObject guardedObject = new GuardedObject(generateId());
        boxes.put(guardedObject.getId(), guardedObject);
        return guardedObject;
    }

    /**
     * 根据id获取结果对象
     * @param id 结果对象id
     * @return 结果对象值
     */
    public static GuardedObject getGuardedObject(int id) {
        return boxes.get(id);
        // return boxes.remove(id);
    }

    // /**
    //  * 获取所有id
    //  * @return 所有id集合
    //  */
    // public static Set<Integer> getIds() {
    //     return boxes.keySet();
    // }
}
