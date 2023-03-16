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

    /**
     * 清理gateway，如果gateway不为空，则会调用gateway.close()
     */
    void clearGateway();
}
