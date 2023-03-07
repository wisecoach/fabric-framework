package com.wisecoach.security.spring.interceptor;

import com.wisecoach.security.user.UserAttributeSource;
import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * 事务通知类
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午1:45
 * {@code @version:} 1.0.0
 */
public class UserAttributeSourceAdvisor extends AbstractPointcutAdvisor {

    private FabricTransactionInterceptor interceptor;

    private final UserAttributeSourcePointcut pointcut = new UserAttributeSourcePointcut() {
        @Override
        protected UserAttributeSource getUserAttributeSource() {
            return (interceptor != null ? interceptor.getUserAttributeSource() : null);
        }
    };

    public UserAttributeSourceAdvisor() {
    }

    public UserAttributeSourceAdvisor(FabricTransactionInterceptor interceptor) {
        setInterceptor(interceptor);
    }

    public void estClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return interceptor;
    }

    public void setInterceptor(FabricTransactionInterceptor interceptor) {
        this.interceptor = interceptor;
    }
}
