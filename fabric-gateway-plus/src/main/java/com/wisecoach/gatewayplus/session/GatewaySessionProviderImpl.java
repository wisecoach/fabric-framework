package com.wisecoach.gatewayplus.session;

import com.wisecoach.gatewayplus.info.GatewayInfo;
import com.wisecoach.util.Assert;
import org.hyperledger.fabric.client.Gateway;

/**
 * 一个默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:50
 * {@code @version:} 1.0.0
 */

public class GatewaySessionProviderImpl implements GatewaySessionProvider {
    @Override
    public GatewaySession getGatewaySession(GatewayInfo gatewayInfo) {
        Assert.notNull(gatewayInfo, "gatewayInfo不可为空，请在合适的阶段提供gatewayInfo");

        return null;
    }
}
