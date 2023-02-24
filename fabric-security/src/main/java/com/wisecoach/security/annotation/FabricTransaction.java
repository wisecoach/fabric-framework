package com.wisecoach.security.annotation;

import com.wisecoach.security.user.UserProvider;

/**
 * 用于指明一个Service方法需要与Fabric交互
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午10:11
 * {@code @version:} 1.0.0
 */

public @interface FabricTransaction {
    /**
     * 是否使用FabricTransaction
     */
    boolean value() default true;

    /**
     * 限定使用的providers，要求该方法必须采用继承自提供的providers的实现对象来提供User
     * @return providers
     */
    Class<? extends UserProvider>[] providers() default {};
}
