package com.wisecoach.gatewayplus.transaction;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:03
 * {@code @version:} 1.0.0
 */

public interface TransactionContextProvider {
    TransactionContext getTransactionContext(TransactionStrategy strategy);
}
