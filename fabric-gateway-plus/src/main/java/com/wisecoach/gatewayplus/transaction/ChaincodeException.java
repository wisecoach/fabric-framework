package com.wisecoach.gatewayplus.transaction;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/8 上午11:05
 * {@code @version:} 1.0.0
 */


public class ChaincodeException extends TransactionException {

    public ChaincodeException() {
    }

    public ChaincodeException(String message) {
        super(message);
    }

    public ChaincodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
