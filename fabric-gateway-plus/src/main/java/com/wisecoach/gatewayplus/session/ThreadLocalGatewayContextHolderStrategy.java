package com.wisecoach.gatewayplus.session;

import com.wisecoach.util.Assert;

/**
 * 使用ThreadLocal存储GatewayContext，保证每个处理请求的线程各自存储其GatewayContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:28
 * {@code @version:} 1.0.0
 */


public class ThreadLocalGatewayContextHolderStrategy implements GatewayContextHolderStrategy {

    private static final ThreadLocal<GatewayContext> contextHolder = new ThreadLocal<>();

    @Override
    public void setGatewayContext(GatewayContext context) {
        Assert.notNull(context, "不可存储空的context");
        contextHolder.set(context);
    }

    @Override
    public GatewayContext getGatewayContext() {
        GatewayContext context = contextHolder.get();
        if (context == null) {
            context = getEmptyGatewayContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void clearGatewayContext() {
        contextHolder.remove();
    }

    @Override
    public GatewayContext getEmptyGatewayContext() {
        return new GatewayContextImpl();
    }
}
