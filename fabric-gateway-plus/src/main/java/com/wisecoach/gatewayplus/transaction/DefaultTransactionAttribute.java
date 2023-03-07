package com.wisecoach.gatewayplus.transaction;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:06
 * {@code @version:} 1.0.0
 */

public class DefaultTransactionAttribute implements TransactionAttribute {

    private final TransactionStrategy strategy;
    private final String name;

    public DefaultTransactionAttribute(TransactionStrategy strategy, String name) {
        this.strategy = strategy;
        this.name = name;
    }

    @Override
    public TransactionStrategy getTransactionStrategy() {
        return strategy;
    }

    @Override
    public String getName() {
        return name;
    }
}
