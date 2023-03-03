package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.transaction.AbstractTransactionContext;
import com.wisecoach.gatewayplus.transaction.TransactionContext;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;

/**
 * {@link TransactionStrategy#SUBMIT_ASYNC} 策略的 {@link TransactionContext}
 * 该策略需要通知
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 上午9:45
 * {@code @version:} 1.0.0
 */


public class SubmitAsyncTransactionContext extends AbstractTransactionContext {

    

    @Override
    public TransactionStrategy getStrategy() {
        return TransactionStrategy.SUBMIT_ASYNC;
    }

}
