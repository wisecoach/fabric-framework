package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.transaction.TransactionAdvice;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;

import java.lang.reflect.Method;

/**
 * 为了实现{@link TransactionStrategy#SUBMIT_ASYNC}策略的Service层处理方式
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午6:27
 * {@code @version:} 1.0.0
 */


public class SubmitAsyncAdvice implements TransactionAdvice {

    /**
     * 该策略下，啥事不用做
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     */
    @Override
    public void before(Method method, Object[] args, Object target) {

    }

    /**
     * 好像真啥都不用做
     * @param ret 返回结果
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     */
    @Override
    public void afterReturning(Object ret, Method method, Object[] args, Object target) {

    }

    /**
     * 抛出即可
     * @param method 要调用的Serivce方法
     * @param args 方法参数
     * @param target service对象
     * @param t 抛出的异常
     */
    @Override
    public void throwing(Method method, Object[] args, Object target, Throwable t) throws Throwable {
        throw t;
    }
}
