package com.wisecoach.security.userinfo;

/**
 * 全局存储 {@link UserInfoContext} 的Holder，可以采用的策略 {@link UserInfoContextHolderStrategy }去存储
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:16
 * {@code @version:} 1.0.0
 */

public class UserInfoContextHolder {

    private static UserInfoContextHolderStrategy strategy;

    static {
        initialize();
    }

    private static void initialize() {
        // 目前还只支持这一种实现
        strategy = new ThreadLocalUserInfoContextHolderStrategy();
    }

    public static UserInfoContext getContext() {
        return strategy.getUserInfoContext();
    }

    public static void setContext(UserInfoContext context) {
        strategy.setUserInfoContext(context);
    }

    public static void clearContext() {
        strategy.clearUserInfoContext();
    }

}
