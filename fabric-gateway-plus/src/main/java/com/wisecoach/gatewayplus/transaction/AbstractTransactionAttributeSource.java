package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.annotation.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个抽象类，完成了取得TransactionAttribute的核心逻辑，具体实现留给findTransactionAttribute，此外了提供了缓存的功能；
 * 和spring-tx不同的是，这里不提供对Class的getTxAttr，因为这个框架的不会提供对整个类的支持，只支持具体的方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:16
 * {@code @version:} 1.0.0
 */


public abstract class AbstractTransactionAttributeSource implements TransactionAttributeSource {

    /**
     * 非事务方法的缓存占位符
     */
    private static final TransactionAttribute NULL_TRANSACTION_ATTRIBUTE = new DefaultTransactionAttribute(TransactionStrategy.SUBMIT_ASYNC, "null") {
        @Override
        public String toString() {
            return "null";
        }
    };

    /**
     * 方法到事务属性的缓存
     */
    private final Map<Method, TransactionAttribute> cachedTxAttrs = new ConcurrentHashMap<>();

    @Override
    @Nullable
    public TransactionAttribute getTransactionAttribute(Method method, @Nullable Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        TransactionAttribute cached = cachedTxAttrs.get(method);
        if (cached != null) {
            if (cached == NULL_TRANSACTION_ATTRIBUTE) {
                return null;
            }
            return cached;
        } else {
            cached = computeTransactionAttribute(method, targetClass);
            if (cached == null) {
                cachedTxAttrs.put(method, NULL_TRANSACTION_ATTRIBUTE);
            } else {
                cachedTxAttrs.put(method, cached);
            }
        }
        return cached;
    }

    protected TransactionAttribute computeTransactionAttribute(Method method, @Nullable Class<?> targetClass) {
        // 只判断public的方法
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        return findTransactionAttribute(method);
    }

    protected abstract TransactionAttribute findTransactionAttribute(Method method);

}
