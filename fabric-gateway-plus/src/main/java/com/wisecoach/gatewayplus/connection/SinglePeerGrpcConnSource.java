package com.wisecoach.gatewayplus.connection;

import com.wisecoach.util.Assert;
import io.grpc.ManagedChannel;

import java.security.cert.X509Certificate;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午5:09
 * {@code @version:} 1.0.0
 */


public class SinglePeerGrpcConnSource implements GrpcConnSource {

    /**
     * 单节点的连接信息
     */
    private GrpcConnInfo info;

    @Override
    public ManagedChannel getConnection(GrpcConnKey connKey) {
        GrpcConnKey key = checkAndTransform(connKey);
        if (info == null) {
            throw new GrpcConnException("还没有设置单节点的连接信息");
        }
        return GrpcConnUtil.newGrpcChannel(info);
    }

    @Override
    public void register(GrpcConnKey connKey, String endpoint, X509Certificate tlsCert, String authority) {
        SinglePeerKey key = checkAndTransform(connKey);
        info = new GrpcConnInfo(endpoint, tlsCert, authority);
    }

    private SinglePeerKey checkAndTransform(GrpcConnKey connKey) {
        // 取得GrpcConnInfo
        Assert.notNull(connKey, "key不可为空");
        if (!(connKey instanceof SinglePeerKey)) {
            throw new GrpcConnException("该连接源不支持此类MspKey");
        }
        return (SinglePeerKey) connKey;
    }
}
