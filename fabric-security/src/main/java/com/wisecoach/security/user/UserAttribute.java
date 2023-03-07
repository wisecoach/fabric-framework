package com.wisecoach.security.user;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:47
 * {@code @version:} 1.0.0
 */

public interface UserAttribute {
    /**
     * 取得事务名，默认为 {@code qualified class name + "." + method name}
     */
    String getName();

    /**
     * 限定使用的providers，要求该方法必须采用继承自提供的providers的实现对象来提供User
     */
    Class<? extends UserProvider>[] getProviderClasses();
}
