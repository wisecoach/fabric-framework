package com.wisecoach.gatewayplus.transaction;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 上午9:40
 * {@code @version:} 1.0.0
 */


public abstract class AbstractTransactionContext implements TransactionContext {

    @Override
    public Long getId() {
        return Thread.currentThread().getId();
    }

}
