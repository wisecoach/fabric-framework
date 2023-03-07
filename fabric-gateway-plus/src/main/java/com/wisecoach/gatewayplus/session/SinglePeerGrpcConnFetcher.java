package com.wisecoach.gatewayplus.session;

import com.wisecoach.gatewayplus.connection.GrpcConnSource;
import com.wisecoach.gatewayplus.connection.SinglePeerKey;
import com.wisecoach.gatewayplus.info.GatewayInfo;
import io.grpc.ManagedChannel;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午2:00
 * {@code @version:} 1.0.0
 */


public class SinglePeerGrpcConnFetcher implements GrpcConnFetcher {

    private final GrpcConnSource grpcConnSource;

    public SinglePeerGrpcConnFetcher(GrpcConnSource grpcConnSource) {
        this.grpcConnSource = grpcConnSource;
    }

    @Override
    public ManagedChannel fetchConn(GatewayInfo info) {
        return grpcConnSource.getConnection(SinglePeerKey.INSTANCE);
    }
}
