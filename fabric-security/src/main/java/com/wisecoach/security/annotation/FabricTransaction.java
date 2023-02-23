package com.wisecoach.security.annotation;

/**
 * 用于指明一个Service方法需要与Fabric交互
 * @author: wisecoach
 * @date: 2023/2/23 上午10:11
 * @version: 1.0.0
 */

public @interface FabricTransaction {
    /**
     * 是否使用FabricTransaction
     */
    boolean value() default true;


}
