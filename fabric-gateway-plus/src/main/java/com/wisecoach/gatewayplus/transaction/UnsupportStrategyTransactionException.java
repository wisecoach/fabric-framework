package com.wisecoach.gatewayplus.transaction;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:06
 * {@code @version:} 1.0.0
 */


public class UnsupportStrategyTransactionException extends TransactionException {

    public UnsupportStrategyTransactionException() {
    }

    public UnsupportStrategyTransactionException(String message) {
        super(message);
    }

    public UnsupportStrategyTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
