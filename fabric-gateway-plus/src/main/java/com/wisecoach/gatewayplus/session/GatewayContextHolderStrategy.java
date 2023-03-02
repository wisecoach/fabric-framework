package com.wisecoach.gatewayplus.session;

import com.wisecoach.annotation.NotNull;

/**
 * {@link GatewayContextHolder} 的策略，对应可以采用不同的方式去实现存储GatewayContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:22
 * {@code @version:} 1.0.0
 */

public interface GatewayContextHolderStrategy {
    /**
     * 设置当前context
     * @param context
     */
    void setGatewayContext(GatewayContext context);

    /**
     * 取得当前context
     * @return context
     */
    @NotNull
    GatewayContext getGatewayContext();

    /**
     * 清除当前的context
     */
    void clearGatewayContext();

    /**
     * 取得空的GatewayContext
     * @return context
     */
    GatewayContext getEmptyGatewayContext();
}
