package com.wsw01;

import lombok.*;

/**
 * @author loriyuhv
 * @date 2025/9/20 10:52
 * @description
 * 不加@Setter：意味着他不可被操作
 * final: 意味着没有子类
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
final public class Message<T> {
    private int id;
    private T value;
}
