package com.wisecoach.gatewayplus.proxy;

import com.wisecoach.gatewayplus.annotation.ChaincodeTransaction;
import com.wisecoach.gatewayplus.info.GatewayInfo;
import com.wisecoach.gatewayplus.info.GatewayInfoProvider;
import com.wisecoach.gatewayplus.session.*;
import com.wisecoach.gatewayplus.transaction.TransactionAdvice;
import com.wisecoach.gatewayplus.transaction.TransactionContext;
import com.wisecoach.gatewayplus.transaction.TransactionContextHolder;
import com.wisecoach.gatewayplus.transaction.TransactionContextProvider;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Service方法拦截器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:56
 * {@code @version:} 1.0.0
 */
public class ChaincodeTransactionInterceptor implements MethodInterceptor {

    private final TransactionAdvice advice;
    private final TransactionContextProvider transactionContextProvider;
    private final GatewayInfoProvider gatewayInfoProvider;
    private final GatewaySessionProvider gatewaySessionProvider;

    public ChaincodeTransactionInterceptor(
            TransactionAdvice advice,
            TransactionContextProvider transactionContextProvider,
            GatewayInfoProvider gatewayInfoProvider,
            GatewaySessionProvider gatewaySessionProvider) {
        this.advice = advice;
        this.transactionContextProvider = transactionContextProvider;
        this.gatewayInfoProvider = gatewayInfoProvider;
        this.gatewaySessionProvider = gatewaySessionProvider;
    }

    /**
     * 大致流程如下：
     * 1. 首先要判断方法是否为 ChaincodeTransaction {@link ChaincodeTransaction}
     * 2. 读取事务的策略 {@link TransactionStrategy}
     * 3. 根据策略生成对应的事务上下文 {@link TransactionContext}，并且将上下文放入到 {@link TransactionContextHolder} 中
     * 4. 取得gatewayInfo，然后生成对应的gateway，将gateway存入到GatewayContextHolder
     * 5. 执行 {@link TransactionAdvice#before(Method, Object[], Object)}
     * 6. 执行被代理加强方法
     * 7. 执行 {@link TransactionAdvice#afterReturning(Object, Method, Object[], Object)}
     * 8. 若期间抛出异常 {@link TransactionAdvice#throwing(Method, Object[], Object, Throwable)}
     * 9. 清空 {@link TransactionContextHolder} 和 {@link GatewayContextHolder}
     * 10. 返回结果
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 1
        ChaincodeTransaction chaincodeTransaction = method.getAnnotation(ChaincodeTransaction.class);
        if (chaincodeTransaction == null) {
            return proxy.invokeSuper(obj, args);
        }
        // 2
        TransactionStrategy strategy = chaincodeTransaction.value();
        // 3
        TransactionContext transactionContext = transactionContextProvider.getTransactionContext(strategy);
        TransactionContextHolder.setContext(transactionContext);
        try {
            // 4
            GatewayInfo gatewayInfo = gatewayInfoProvider.getGatewayInfo(obj, method, args);
            GatewaySession gatewaySession = gatewaySessionProvider.getGatewaySession(gatewayInfo);
            GatewayContext gatewayContext = GatewayContextHolder.getEmptyContext();
            gatewayContext.setGateway(gatewaySession);
            GatewayContextHolder.setContext(gatewayContext);
            // 5
            advice.before(method, args, obj);
            // 6
            Object ret = proxy.invokeSuper(obj, args);
            // 7
            advice.afterReturning(ret, method, args, obj);
            // 9
            TransactionContextHolder.clearContext();
            GatewayContextHolder.clearContext();
            // 10
            return ret;
        } catch (Throwable t) {
            // 8
            advice.throwing(method, args, obj, t);
            throw t;
        }
    }
}
