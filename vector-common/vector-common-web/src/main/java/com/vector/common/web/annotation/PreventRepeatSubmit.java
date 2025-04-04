package com.vector.common.web.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreventRepeatSubmit {
    
    enum Type { TOKEN, PARAM };

    Type type() default Type.TOKEN;

    int interval() default 5;
}
