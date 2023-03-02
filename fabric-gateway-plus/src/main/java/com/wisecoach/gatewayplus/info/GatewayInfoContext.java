package com.wisecoach.gatewayplus.info;

/**
 * GatewayInfo的上下文
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:07
 * {@code @version:} 1.0.0
 */

public interface GatewayInfoContext {
    GatewayInfo getGatewayInfo();
    void setGatewayInfo(GatewayInfo gatewayInfo);
}
