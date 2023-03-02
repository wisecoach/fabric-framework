package com.wisecoach.gatewayplus.info;

import com.wisecoach.annotation.NotNull;

/**
 * {@link GatewayInfoContextHolder} 的策略，对应可以采用不同的方式去实现存储GatewayInfoContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:22
 * {@code @version:} 1.0.0
 */

public interface GatewayInfoContextHolderStrategy {
    /**
     * 设置当前context
     * @param context
     */
    void setGatewayInfoContext(GatewayInfoContext context);

    /**
     * 取得当前context
     * @return context
     */
    @NotNull
    GatewayInfoContext getGatewayInfoContext();

    /**
     * 清除当前的context
     */
    void clearGatewayInfoContext();

    /**
     * 取得空的GatewayInfoContext
     * @return context
     */
    GatewayInfoContext getEmptyGatewayInfoContext();
}
