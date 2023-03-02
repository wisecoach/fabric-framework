package com.wisecoach.gatewayplus.info;

/**
 * 全局存储 {@link GatewayInfoContext} 的Holder，可以采用的策略 {@link GatewayInfoContextHolderStrategy }去存储
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:16
 * {@code @version:} 1.0.0
 */

public class GatewayInfoContextHolder {

    private static GatewayInfoContextHolderStrategy strategy;

    static {
        initialize();
    }

    private static void initialize() {
        // 目前还只支持这一种实现
        strategy = new ThreadLocalGatewayInfoContextHolderStrategy();
    }

    public static GatewayInfoContext getContext() {
        return strategy.getGatewayInfoContext();
    }

    public static void setContext(GatewayInfoContext context) {
        strategy.setGatewayInfoContext(context);
    }

    public static void clearContext() {
        strategy.clearGatewayInfoContext();
    }

}
