package com.wisecoach.gatewayplus.spring.annotation;

import java.lang.annotation.*;

/**
 * 用于标注出一个类是需要调用链码的Service类
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:41
 * {@code @version:} 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface ChaincodeService {
}
