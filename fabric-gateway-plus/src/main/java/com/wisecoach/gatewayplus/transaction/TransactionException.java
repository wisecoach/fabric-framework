package com.wisecoach.gatewayplus.transaction;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:05
 * {@code @version:} 1.0.0
 */


public class TransactionException extends RuntimeException {

    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
