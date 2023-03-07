package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.annotation.Nullable;
import com.wisecoach.gatewayplus.connection.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:22
 * {@code @version:} 1.0.0
 */

@Configuration
@EnableConfigurationProperties(GatewayPlusConnProperties.class)
public class GatewayPlusConnAutoConfiguration {

    private final GatewayPlusConnProperties properties;

    public GatewayPlusConnAutoConfiguration(GatewayPlusConnProperties properties) {
        this.properties = properties;
    }

    // ------------------- connection ---------------------

    @Bean
    public MspGrpcConnSource mspGrpcConnSource() {
        return null;
    }

    @Bean
    public SinglePeerGrpcConnSource singlePeerGrpcConnSource() {
        RawConnInfo peerInfo = properties.getSinglePeerInfo();
        if (peerInfo == null) {
            return null;
        }
        SinglePeerGrpcConnSource singlePeerGrpcConnSource = new SinglePeerGrpcConnSource();
        GrpcConnInfo info = peerInfo.form();
        singlePeerGrpcConnSource.register(SinglePeerKey.INSTANCE, info.getEndpoint(), info.getTlsCert(), info.getAuthority());
        return singlePeerGrpcConnSource;
    }

    /**
     * 这个注入到容器中，主要是为了注册自定义的GrpcConn管理策略
     */
    @Bean
    public GrpcConnSourceDelegate grpcConnSourceDelegate(
            @Nullable SinglePeerGrpcConnSource singlePeer,
            @Nullable MspGrpcConnSource msp) {
        GrpcConnSourceDelegate delegate = new GrpcConnSourceDelegate();
        if (singlePeer != null) {
            delegate.registerConnSource(SinglePeerKey.class, singlePeer);
        }
        if (msp != null) {
            delegate.registerConnSource(MspKey.class, msp);
        }
        return delegate;
    }

}
