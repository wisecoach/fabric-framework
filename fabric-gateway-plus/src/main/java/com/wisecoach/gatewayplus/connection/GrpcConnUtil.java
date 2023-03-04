package com.wisecoach.gatewayplus.connection;

import com.wisecoach.util.Assert;
import com.wisecoach.util.StringUtils;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import javax.net.ssl.SSLException;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午5:11
 * {@code @version:} 1.0.0
 */


public class GrpcConnUtil {

    public static ManagedChannel newGrpcChannel(GrpcConnInfo info) {
        // 设置端点
        String endpoint = info.getEndpoint();
        Assert.hasLength(endpoint, "endpoint不可为空");
        NettyChannelBuilder builder = NettyChannelBuilder.forTarget(endpoint);
        // 设置tls环境
        if (info.getTlsCert() != null) {
            try {
                builder.sslContext(GrpcSslContexts.forClient().trustManager(info.getTlsCert()).build());
            } catch (SSLException e) {
                throw new RuntimeException(e);
            }
        }
        // 设置authority
        if (StringUtils.hasLength(info.getAuthority())) {
            builder.overrideAuthority(info.getAuthority());
        }
        return builder.build();
    }

}
