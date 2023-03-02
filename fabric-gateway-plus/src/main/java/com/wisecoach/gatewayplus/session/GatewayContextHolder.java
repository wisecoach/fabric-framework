package com.wisecoach.gatewayplus.session;

/**
 * 全局存储 {@link GatewayContext} 的Holder，可以采用的策略 {@link GatewayContextHolderStrategy }去存储
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:16
 * {@code @version:} 1.0.0
 */

public class GatewayContextHolder {

    private static GatewayContextHolderStrategy strategy;

    static {
        initialize();
    }

    private static void initialize() {
        // 目前还只支持这一种实现
        strategy = new ThreadLocalGatewayContextHolderStrategy();
    }

    public static GatewayContext getContext() {
        return strategy.getGatewayContext();
    }

    public static void setContext(GatewayContext context) {
        strategy.setGatewayContext(context);
    }

    public static void clearContext() {
        strategy.clearGatewayContext();
    }

}
