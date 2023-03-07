package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.gatewayplus.connection.GrpcConnInfo;
import com.wisecoach.gatewayplus.connection.RawConnInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:22
 * {@code @version:} 1.0.0
 */


@ConfigurationProperties(GatewayPlusConnProperties.prefix)
public class GatewayPlusConnProperties {

    public static final String prefix = "fabric.gatewayplus.conn";

    @NestedConfigurationProperty
    private RawConnInfo singlePeerInfo;

    public RawConnInfo getSinglePeerInfo() {
        return singlePeerInfo;
    }

    public void setSinglePeerInfo(RawConnInfo singlePeerInfo) {
        this.singlePeerInfo = singlePeerInfo;
    }
}
