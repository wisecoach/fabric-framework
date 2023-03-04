package com.wisecoach.gatewayplus.connection;

import com.wisecoach.util.Assert;
import io.grpc.ManagedChannel;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * MSP级别的GRPCChannelSource，根据mspID作为key来创建GRPC连接，主要用于一个组织固定连接其组织的一个peer节点的场景
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
        return GrpcConnUtil.newGrpcChannel(grpcConnInfo);
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint, X509Certificate tlsCert, String authority) {
        MspKey key = checkAndTransform(connKey);
        grpcInfos.put(key, new GrpcConnInfo(endpoint, tlsCert, authority));
    }

    private MspKey checkAndTransform(GrpcConnKey connKey) {
        // 取得GrpcConnInfo
        Assert.notNull(connKey, "key不可为空");
        if (!(connKey instanceof MspKey)) {
            throw new GrpcConnException("该连接源不支持此类MspKey");
        }
        return (MspKey) connKey;
    }
}
