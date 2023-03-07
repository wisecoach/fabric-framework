package com.wisecoach.security.spring.interceptor;

import com.wisecoach.security.user.UserAttributeSource;
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


abstract class UserAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    protected UserAttributeSourcePointcut() {
        setClassFilter(new UserAttributeSourceClassFilter());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        UserAttributeSource source = getUserAttributeSource();
        return (source == null || source.getUserAttribute(method, targetClass) != null);
    }

    @Override
    public int hashCode() {
        return UserAttributeSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getUserAttributeSource();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserAttributeSourcePointcut)) {
            return false;
        }
        UserAttributeSourcePointcut otherPc = (UserAttributeSourcePointcut) other;
        return ObjectUtils.nullSafeEquals(getUserAttributeSource(), otherPc.getUserAttributeSource());
    }

    protected abstract UserAttributeSource getUserAttributeSource();

    private class UserAttributeSourceClassFilter implements ClassFilter {

        @Override
        public boolean matches(Class<?> clazz) {
            UserAttributeSource source = getUserAttributeSource();
            return (source == null || source.isCandidateClass(clazz));
        }
    }
}
