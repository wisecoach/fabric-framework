package com.wisecoach.gatewayplus.info;

import com.wisecoach.util.Assert;

/**
 * 使用ThreadLocal存储GatewayInfoContext，保证每个处理请求的线程各自存储其GatewayInfoContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:28
 * {@code @version:} 1.0.0
 */


public class ThreadLocalGatewayInfoContextHolderStrategy implements GatewayInfoContextHolderStrategy {

    private static final ThreadLocal<GatewayInfoContext> contextHolder = new ThreadLocal<>();

    @Override
    public void setGatewayInfoContext(GatewayInfoContext context) {
        Assert.notNull(context, "不可存储空的context");
        contextHolder.set(context);
    }

    @Override
    public GatewayInfoContext getGatewayInfoContext() {
        GatewayInfoContext context = contextHolder.get();
        if (context == null) {
            context = getEmptyGatewayInfoContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void clearGatewayInfoContext() {
        contextHolder.remove();
    }

    @Override
    public GatewayInfoContext getEmptyGatewayInfoContext() {
        return new GatewayInfoContextImpl();
    }
}
