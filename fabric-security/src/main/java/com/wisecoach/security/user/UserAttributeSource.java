package com.wisecoach.security.user;

import com.wisecoach.annotation.Nullable;

import java.lang.reflect.Method;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:50
 * {@code @version:} 1.0.0
 */

public interface UserAttributeSource {
    /**
     * 判断目标类是否是一个可以提供UserAttribute的候选类
     */
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    /**
     * 取得方法所对应的事务属性，如果方法不是事务的，那么返回null
     */
    @Nullable
    UserAttribute getUserAttribute(Method method, @Nullable Class<?> targetClass);

}
