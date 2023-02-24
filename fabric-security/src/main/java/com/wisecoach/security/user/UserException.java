package com.wisecoach.security.user;

/**
 * 取得用户时会导致的各种错误
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午3:15
 * {@code @version:} 1.0.0
 */


public class UserException extends RuntimeException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
