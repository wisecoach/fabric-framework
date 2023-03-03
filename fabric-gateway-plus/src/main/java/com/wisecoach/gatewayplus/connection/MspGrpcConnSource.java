package com.wisecoach.gatewayplus.connection;

import com.wisecoach.util.Assert;
import com.wisecoach.util.StringUtils;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import javax.net.ssl.SSLException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午9:59
 * {@code @version:} 1.0.0
 */


public class MspGrpcConnSource implements GrpcConnSource {

    private final Map<MspKey, GrpcConnInfo> grpcInfos = new HashMap<>();

    @Override
    public ManagedChannel getConnection(GrpcConnKey connKey) {
        MspKey key = checkAndTransform(connKey);
        GrpcConnInfo grpcConnInfo = grpcInfos.get(key);
        if (grpcConnInfo == null) {
            throw new GrpcConnException("还没有注册这个key:" + key.getKey() + "，需要先注册后才可以使用");
        }
        // 设置端点
        String endpoint = grpcConnInfo.getEndpoint();
        Assert.hasLength(endpoint, "endpoint不可为空");
        NettyChannelBuilder builder = NettyChannelBuilder.forTarget(endpoint);
        // 设置tls环境
        if (grpcConnInfo.getTlsCert() != null) {
            try {
                builder.sslContext(GrpcSslContexts.forClient().trustManager(grpcConnInfo.getTlsCert()).build());
            } catch (SSLException e) {
                throw new RuntimeException(e);
            }
        }
        // 设置authority
        if (StringUtils.hasLength(grpcConnInfo.getAuthority())) {
            builder.overrideAuthority(grpcConnInfo.getAuthority());
        }
        return builder.build();
    }

    private MspKey checkAndTransform(GrpcConnKey connKey) {
        // 取得GrpcConnInfo
        Assert.notNull(connKey, "key不可为空");
        if (!(connKey instanceof MspKey)) {
            throw new GrpcConnException("该连接源不支持此类MspKey");
        }
        return (MspKey) connKey;
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint) {
        MspKey key = checkAndTransform(connKey);
        grpcInfos.put(key, new GrpcConnInfo(endpoint, null, null));
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint, String authority) {
        MspKey key = checkAndTransform(connKey);
        grpcInfos.put(key, new GrpcConnInfo(endpoint, null, authority));
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint, X509Certificate tlsCert) {
        MspKey key = checkAndTransform(connKey);
        grpcInfos.put(key, new GrpcConnInfo(endpoint, tlsCert, null));
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint, X509Certificate tlsCert, String authority) {
        MspKey key = checkAndTransform(connKey);
        grpcInfos.put(key, new GrpcConnInfo(endpoint, tlsCert, authority));
    }
}
