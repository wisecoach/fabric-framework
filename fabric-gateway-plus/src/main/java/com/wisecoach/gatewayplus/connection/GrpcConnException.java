package com.wisecoach.gatewayplus.connection;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午10:01
 * {@code @version:} 1.0.0
 */


public class GrpcConnException extends RuntimeException {
    public GrpcConnException() {
    }

    public GrpcConnException(String message) {
        super(message);
    }

    public GrpcConnException(String message, Throwable cause) {
        super(message, cause);
    }
}
