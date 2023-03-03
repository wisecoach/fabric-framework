package com.wisecoach.gatewayplus.transaction;

/**
 * TransactionContext的ThreadLocal持有者，不装了，我不可能在这扩展了，别策略了
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:54
 * {@code @version:} 1.0.0
 */

public class TransactionContextHolder {
    private final static ThreadLocal<TransactionContext> contextHolder = new ThreadLocal<>();

    public static TransactionContext getContext() {
        return contextHolder.get();
    }

    public static void setContext(TransactionContext context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
