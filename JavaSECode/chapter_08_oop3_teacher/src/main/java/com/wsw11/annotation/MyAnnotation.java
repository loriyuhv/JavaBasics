package com.wsw11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR})
public @interface MyAnnotation {
    // 属性
    String value() default "class";
}
