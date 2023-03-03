package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.gatewayplus.transaction.async.SubmitAsyncTransactionContext;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:04
 * {@code @version:} 1.0.0
 */


public class DefaultTransactionContextProvider implements TransactionContextProvider {

    @Override
    public TransactionContext getTransactionContext(TransactionStrategy strategy) {
        TransactionContext context = null;
        switch (strategy) {
            case SUBMIT_ASYNC:
                context = new SubmitAsyncTransactionContext();
                break;
            case SUBMIT_SYNC:
            case TX_SUBMIT:
            case LOCKED_TX_SUBMIT:
            default:
                throw new UnsupportStrategyTransactionException("不支持的策略:" + strategy.toString());
        }
        return context;
    }
}
