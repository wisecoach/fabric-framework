package com.wisecoach.gatewayplus.spring.interceptor;

import com.wisecoach.gatewayplus.transaction.TransactionAttributeSource;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 事务属性源切点，用于将整个框架通过AOP注册到容器中，寻找合适的位置作为切点，提供额外加强服务
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午1:46
 * {@code @version:} 1.0.0
 */


abstract class TransactionAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    protected TransactionAttributeSourcePointcut() {
        setClassFilter(new TransactionAttributeSourceClassFilter());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        TransactionAttributeSource source = getTransactionAttributeSource();
        return (source == null || source.getTransactionAttribute(method, targetClass) != null);
    }

    @Override
    public int hashCode() {
        return TransactionAttributeSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getTransactionAttributeSource();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransactionAttributeSourcePointcut)) {
            return false;
        }
        TransactionAttributeSourcePointcut otherPc = (TransactionAttributeSourcePointcut) other;
        return ObjectUtils.nullSafeEquals(getTransactionAttributeSource(), otherPc.getTransactionAttributeSource());
    }

    protected abstract TransactionAttributeSource getTransactionAttributeSource();

    private class TransactionAttributeSourceClassFilter implements ClassFilter {

        @Override
        public boolean matches(Class<?> clazz) {
            TransactionAttributeSource source = getTransactionAttributeSource();
            return (source == null || source.isCandidateClass(clazz));
        }
    }
}
