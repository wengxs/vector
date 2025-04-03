package com.vector.common.web.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交注解
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreventRepeatSubmit {

    int interval() default 5;
}
