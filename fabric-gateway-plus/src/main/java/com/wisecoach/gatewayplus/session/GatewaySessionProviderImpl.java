package com.wisecoach.gatewayplus.session;

import org.hyperledger.fabric.client.Gateway;

/**
 * 一个默认实现，new就完了
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:50
 * {@code @version:} 1.0.0
 */


public class GatewaySessionProviderImpl implements GatewaySessionProvider {
    @Override
    public GatewaySession getGatewaySession(Gateway gateway) {
        return new GatewaySession(gateway);
    }
}
