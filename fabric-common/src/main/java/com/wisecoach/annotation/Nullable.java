package com.wisecoach.annotation;

import java.lang.annotation.*;

/**
 * 指示该方法返回值，方法参数，字段允许为空
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午1:46
 * {@code @version:} 1.0.0
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Nullable {

}
