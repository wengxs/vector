package com.vector.common.core.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = EnumDescSerializer.class)
public @interface JsonEnumDesc {

    /**
     * 覆盖字段
     * 为true时不新增字段，只覆盖字段；
     * 为false时新增字段，不覆盖原字段。
     * @return
     */
    boolean cover() default false;

    /**
     * 新增字段名，不填则新增的字段名为：{fieldName} + {enumFieldName}(驼峰模式，如statusDesc)
     * @return
     */
    String newFieldName() default "";

    /**
     * 枚举字段
     * 期望输入的枚举字段值
     * @return
     */
    String enumFieldName() default "desc";
}
