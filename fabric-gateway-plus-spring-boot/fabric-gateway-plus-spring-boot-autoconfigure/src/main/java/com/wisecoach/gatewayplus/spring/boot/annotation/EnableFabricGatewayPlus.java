package com.wisecoach.gatewayplus.spring.boot.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/6/11 下午2:11
 * {@code @version:} 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({GatewayPlusConfigurationSelector.class})
public @interface EnableFabricGatewayPlus {
    FabricGatewayPlusMode mode() default FabricGatewayPlusMode.ENABLE;
}
