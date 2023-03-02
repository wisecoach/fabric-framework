package com.wisecoach.gatewayplus.session;

import org.hyperledger.fabric.client.Gateway;

/**
 * Gateway的上下文
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:07
 * {@code @version:} 1.0.0
 */

public interface GatewayContext {
    Gateway getGateway();
    void setGateway(Gateway gateway);
}
