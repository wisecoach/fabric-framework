package com.wisecoach.gatewayplus.transaction;

import java.lang.reflect.Method;

/**
 * 用于实现不同事务策略的Advice，它会处理从service方法执行前、执行后、抛出异常的三种情况进行不同的处理，从而实现不同策略的事务
 * 这个不是真的 SpringAOP 里面的Advice，实际上的Advice是interceptor，不过interceptor中的实现不同事务策略的不同逻辑由该接口的子类实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:09
 * {@code @version:} 1.0.0
 */

public interface TransactionAdvice {
    /**
     * service方法被调用前的加强方法
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     */
    void before(Method method, Object[] args, Object target);

    /**
     * service方法返回结果后的加强方法
     * @param ret 返回结果
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     */
    void afterReturning(Object ret, Method method, Object[] args, Object target);

    /**
     * service方法抛出异常的加强方法
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     * @param t 抛出的异常
     */
    void throwing(Method method, Object[] args, Object target, Throwable t) throws Throwable;
}
