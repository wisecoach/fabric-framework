package com.wisecoach.gatewayplus.session;

import com.wisecoach.gatewayplus.connection.GrpcConnSource;
import com.wisecoach.gatewayplus.info.GatewayInfo;
import com.wisecoach.util.Assert;
import io.grpc.ManagedChannel;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.identity.Identity;
import org.hyperledger.fabric.client.identity.X509Identity;

/**
 * 一个默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:50
 * {@code @version:} 1.0.0
 */

public class GatewaySessionProviderImpl implements GatewaySessionProvider {

    private final GrpcConnFetcher grpcConnFetcher;

    public GatewaySessionProviderImpl(GrpcConnFetcher grpcConnFetcher) {
        this.grpcConnFetcher = grpcConnFetcher;
    }

    @Override
    public GatewaySession getGatewaySession(GatewayInfo gatewayInfo) {
        Assert.notNull(gatewayInfo, "gatewayInfo不可为空，请在合适的阶段提供gatewayInfo");
        ManagedChannel channel = grpcConnFetcher.fetchConn(gatewayInfo);
        Gateway gateway = Gateway.newInstance()
                .identity(new X509Identity(gatewayInfo.getMspId(), gatewayInfo.getCertificate()))
                .connection(channel)
                .signer(gatewayInfo.getSigner())
                .connect();
        return new GatewaySession(gateway, channel);
    }
}
