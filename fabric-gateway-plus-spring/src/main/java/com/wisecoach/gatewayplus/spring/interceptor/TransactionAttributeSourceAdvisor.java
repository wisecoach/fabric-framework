package com.wisecoach.gatewayplus.spring.interceptor;

import com.wisecoach.gatewayplus.transaction.TransactionAttributeSource;
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
public class TransactionAttributeSourceAdvisor extends AbstractPointcutAdvisor {

    private ChaincodeTransactionInterceptor interceptor;

    private final TransactionAttributeSourcePointcut pointcut = new TransactionAttributeSourcePointcut() {
        @Override
        protected TransactionAttributeSource getTransactionAttributeSource() {
            return (interceptor != null ? interceptor.getTransactionAttributeSource() : null);
        }
    };

    public TransactionAttributeSourceAdvisor() {
    }

    public TransactionAttributeSourceAdvisor(ChaincodeTransactionInterceptor interceptor) {
        setInterceptor(interceptor);
    }

    public void setClassFilter(ClassFilter classFilter) {
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

    public void setInterceptor(ChaincodeTransactionInterceptor interceptor) {
        this.interceptor = interceptor;
    }
}
