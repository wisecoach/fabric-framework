package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.annotation.Nullable;

import java.lang.reflect.Method;

/**
 * 事务属性源
 * 别想了，就是抄spring-tx的，区块链事务不算事务是吧，给我抄一下怎么了？
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:08
 * {@code @version:} 1.0.0
 */

public interface TransactionAttributeSource {

    /**
     * 判断目标类是否是一个可以提供TransactionAttribute的候选类
     */
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    /**
     * 取得方法所对应的事务属性，如果方法不是事务的，那么返回null
     */
    @Nullable
    TransactionAttribute getTransactionAttribute(Method method, @Nullable Class<?> targetClass);

}
