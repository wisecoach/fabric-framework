package com.wisecoach.gatewayplus.session;

import org.hyperledger.fabric.client.Gateway;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:08
 * {@code @version:} 1.0.0
 */


public class GatewayContextImpl implements GatewayContext {

    private Gateway gateway;

    @Override
    public Gateway getGateway() {
        return gateway;
    }

    @Override
    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void clearGateway() {
        if (gateway != null) {
            gateway.close();
        }
        gateway = null;
    }
}
