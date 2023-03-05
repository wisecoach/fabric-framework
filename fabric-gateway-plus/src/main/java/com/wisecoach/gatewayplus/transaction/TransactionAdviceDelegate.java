package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.gatewayplus.transaction.async.SubmitAsyncAdvice;
import com.wisecoach.util.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link TransactionAdvice} 的 委派器，将任务委派给不同策略的事务通知器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 上午10:15
 * {@code @version:} 1.0.0
 */


public class TransactionAdviceDelegate implements TransactionAdvice {
    /**
     * 事务策略和事务通知的映射
     */
    private final Map<TransactionStrategy, TransactionAdvice> advices = new HashMap<>();

    public TransactionAdviceDelegate() {
        init();
    }

    // {@code TODO 都会把所有实现类写好了再弄这个}
    private void init() {
        register(TransactionStrategy.SUBMIT_ASYNC, new SubmitAsyncAdvice());   
    }

    @Override
    public void before(Method method, Object[] args, Object target) {
        getAdvice().before(method, args, target);
    }

    @Override
    public void afterReturning(Object ret, Method method, Object[] args, Object target) {
        getAdvice().afterReturning(ret, method, args, target);
    }

    @Override
    public void throwing(Method method, Object[] args, Object target, Throwable t) throws Throwable {
        getAdvice().throwing(method, args, target, t);
    }

    /**
     * 注册策略和事务通知的映射
     */
    private void register(TransactionStrategy strategy, TransactionAdvice advice) {
        advices.put(strategy, advice);
    }

    /**
     * 从 {@link TransactionContextHolder} 中取得事务上下文，并且从事务上下文中取得当前交易使用的策略
     * 然后根据策略取得对应的事务通知
     * @return advice
     */
    private TransactionAdvice getAdvice() {
        TransactionContext context = TransactionContextHolder.getContext();
        Assert.notNull(context, "请确保在执行事务通知之前，将TransactionContext注入到TransactionContextHolder中");
        TransactionStrategy strategy = context.getStrategy();
        TransactionAdvice advice = advices.get(strategy);
        if (advice == null) {
            throw new TransactionException("不支持的策略：" + strategy + "\n因为没有该策略的事务通知");
        }
        return advice;
    }
}
