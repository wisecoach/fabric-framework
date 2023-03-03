package com.wisecoach.gatewayplus.session;

import com.wisecoach.gatewayplus.info.GatewayInfo;
import org.hyperledger.fabric.client.Gateway;

/**
 * gatewaySession的提供器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:49
 * {@code @version:} 1.0.0
 */

public interface GatewaySessionProvider {
    /**
     * 根据gatewayInfo 获取 gatewaySession
     * @param gatewayInfo gateway的信息
     * @return gatewaySession
     */
    GatewaySession getGatewaySession(GatewayInfo gatewayInfo);
}
