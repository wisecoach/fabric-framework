package com.wisecoach.gatewayplus.session;

import com.wisecoach.gatewayplus.info.GatewayInfo;
import io.grpc.ManagedChannel;

/**
 * 将根据GatewayInfo信息获取GrpcConn的过程进行了抽象
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:59
 * {@code @version:} 1.0.0
 */

public interface GrpcConnFetcher {
    ManagedChannel fetchConn(GatewayInfo info);
}
