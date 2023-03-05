package com.wisecoach.gatewayplus.bind;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午2:18
 * {@code @version:} 1.0.0
 */


public class BindException extends RuntimeException {
    public BindException() {
    }

    public BindException(String message) {
        super(message);
    }

    public BindException(String message, Throwable cause) {
        super(message, cause);
    }
}
