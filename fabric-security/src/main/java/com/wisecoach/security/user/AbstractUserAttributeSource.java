package com.wisecoach.security.user;

import com.wisecoach.annotation.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个抽象类，完成了取得UserAttribute的核心逻辑，具体实现留给findUserAttribute，此外了提供了缓存的功能；
 * 和spring-tx不同的是，这里不提供对Class的getTxAttr，因为这个框架的不会提供对整个类的支持，只支持具体的方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:16
 * {@code @version:} 1.0.0
 */


public abstract class AbstractUserAttributeSource implements UserAttributeSource {

    /**
     * 非事务方法的缓存占位符
     */
    private static final UserAttribute NULL_USER_ATTRIBUTE = new DefaultUserAttribute("null", null) {
        @Override
        public String toString() {
            return "null";
        }
    };

    /**
     * 方法到事务属性的缓存
     */
    private final Map<Method, UserAttribute> cachedTxAttrs = new ConcurrentHashMap<>();

    @Override
    @Nullable
    public UserAttribute getUserAttribute(Method method, @Nullable Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        UserAttribute cached = cachedTxAttrs.get(method);
        if (cached != null) {
            if (cached == NULL_USER_ATTRIBUTE) {
                return null;
            }
            return cached;
        } else {
            cached = computeUserAttribute(method, targetClass);
            if (cached == null) {
                cachedTxAttrs.put(method, NULL_USER_ATTRIBUTE);
            } else {
                cachedTxAttrs.put(method, cached);
            }
        }

        return cached;
    }

    protected UserAttribute computeUserAttribute(Method method, @Nullable Class<?> targetClass) {
        // 只判断public的方法
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        return findUserAttribute(method);
    }

    protected abstract UserAttribute findUserAttribute(Method method);

}
