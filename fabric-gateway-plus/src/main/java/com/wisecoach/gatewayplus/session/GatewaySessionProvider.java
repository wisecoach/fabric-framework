package com.wisecoach.gatewayplus.session;

import org.hyperledger.fabric.client.Gateway;

/**
 * gatewaySession的提供器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:49
 * {@code @version:} 1.0.0
 */

public interface GatewaySessionProvider {
    /**
     * 将gateway 封装为一个 gatewaySession
     * @param gateway 要被封装的gateway
     * @return gatewaySession
     */
    GatewaySession getGatewaySession(Gateway gateway);
}
