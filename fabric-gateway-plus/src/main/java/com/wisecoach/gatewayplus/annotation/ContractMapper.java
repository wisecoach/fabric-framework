package com.wisecoach.gatewayplus.annotation;

import java.lang.annotation.*;

/**
 * 用于指示一个接口是一个ContractMapper，效果类似Mybatis里面的@Mapper
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午6:41
 * {@code @version:} 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ContractMapper {
    /**
     * 链码名，ContractName
     * @return contractName
     */
    String value();

    /**
     * channel名，该参数可以覆盖默认的channelName
     * @return channelName
     */
    String channelName() default "";
}
