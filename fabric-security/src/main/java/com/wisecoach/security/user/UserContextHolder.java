package com.wisecoach.security.user;

/**
 * 全局存储 {@link UserContext} 的Holder，可以采用的策略 {@link UserContextHolderStrategy }去存储
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:16
 * {@code @version:} 1.0.0
 */

public class UserContextHolder {

    private static UserContextHolderStrategy strategy;

    static {
        initialize();
    }

    private static void initialize() {
        // 目前还只支持这一种实现
        strategy = new ThreadLocalUserContextHolderStrategy();
    }

    public static UserContext getContext() {
        return strategy.getUserContext();
    }

    public static void setContext(UserContext context) {
        strategy.setUserContext(context);
    }

    public static void clearContext() {
        strategy.clearUserContext();
    }

}
