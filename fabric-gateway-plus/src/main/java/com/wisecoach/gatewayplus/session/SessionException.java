package com.wisecoach.gatewayplus.session;

/**
 * Session子模块的产生的异常
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 上午11:06
 * {@code @version:} 1.0.0
 */


public class SessionException extends RuntimeException {
    public SessionException() {
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
