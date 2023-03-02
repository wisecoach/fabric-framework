package com.wisecoach.gatewayplus.info;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:08
 * {@code @version:} 1.0.0
 */


public class GatewayInfoContextImpl implements GatewayInfoContext {

    private GatewayInfo gatewayInfo;

    @Override
    public GatewayInfo getGatewayInfo() {
        return gatewayInfo;
    }

    @Override
    public void setGatewayInfo(GatewayInfo gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }
}
