package com.wisecoach.gatewayplus.connection;

import com.wisecoach.util.Assert;
import io.grpc.ManagedChannel;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * GrpcConnSource的一个委派者，主要将grpcConnKey分发给不同的GrpcConnSource处理
 * 目前支持：
 *      1. MSP级别
 *      2. 单peer节点级
 * @see MspGrpcConnSource
 * @see MspKey
 * @see SinglePeerGrpcConnSource
 * @see SinglePeerKey
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午1:23
 * {@code @version:} 1.0.0
 */


public class GrpcConnSourceDelegate implements GrpcConnSource {

    /**
     * 要委派的GrpcConnSource
     */
    private final Map<Class<? extends GrpcConnKey>, GrpcConnSource> sources = new HashMap<>();

    /**
     * 注册数据源
     * @param keyClazz key的类型
     * @param source key类型对应的数据源
     */
    public void registerConnSource(Class<? extends GrpcConnKey> keyClazz, GrpcConnSource source) {
        sources.put(keyClazz, source);
    }

    @Override
    public ManagedChannel getConnection(GrpcConnKey key) {
        return getSource(key.getClass()).getConnection(key);
    }

    @Override
    public void register(GrpcConnKey key, String endpoint, X509Certificate tlsCert, String authority) {
        getSource(key.getClass()).register(key, endpoint, tlsCert, authority);
    }

    private GrpcConnSource getSource(Class<? extends GrpcConnKey> keyClazz) {
        Assert.notNull(keyClazz, "keyClazz不可为空");
        GrpcConnSource source = sources.get(keyClazz);
        if (source == null) {
            throw new GrpcConnException("不支持此类GrpcConnKey:" + keyClazz.getName());
        }
        return source;
    }
}
