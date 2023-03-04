package com.wisecoach.gatewayplus.connection;

import io.grpc.ManagedChannel;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import java.security.cert.X509Certificate;

/**
 * GrpcConn的装饰器，提供了池化连接的功能
 * 实现上采用了commons-pool2的技术
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午1:54
 * {@code @version:} 1.0.0
 */

public class PooledGrpcConnSource extends BaseKeyedPooledObjectFactory<GrpcConnKey, ManagedChannel>
        implements GrpcConnSource {

    /**
     * 被装饰的 GrpcConnSource
     */
    private final GrpcConnSource grpcConnSource;
    /**
     * ManagedChannel池
     */
    private final KeyedObjectPool<GrpcConnKey, ManagedChannel> pool;

    public PooledGrpcConnSource(GrpcConnSource grpcConnSource) {
        this.grpcConnSource = grpcConnSource;
        this.pool = new GenericKeyedObjectPool<>(this);
    }

    public PooledGrpcConnSource(GrpcConnSource grpcConnSource, GenericKeyedObjectPoolConfig<ManagedChannel> config) {
        this.grpcConnSource = grpcConnSource;
        this.pool = new GenericKeyedObjectPool<>(this, config);
    }

    @Override
    public void register(GrpcConnKey key, String endpoint, X509Certificate tlsCert, String authority) {
        grpcConnSource.register(key, endpoint, tlsCert, authority);
    }

    /**
     * 从池中取得对象
     */
    @Override
    public ManagedChannel getConnection(GrpcConnKey key) {
        try {
            return pool.borrowObject(key);
        } catch (Exception e) {
            throw new GrpcConnException("取得连接失败，", e);
        }
    }

    /**
     * 自己实现的创建pool中对象的方式，实现就是使用被装饰的source去创建
     */
    @Override
    public ManagedChannel create(GrpcConnKey key) throws Exception {
        return grpcConnSource.getConnection(key);
    }

    @Override
    public PooledObject<ManagedChannel> wrap(ManagedChannel value) {
        return new PooledManagedChannel(value);
    }

    /**
     * 主要判断一下 ManagedChannel是否已经被shutdown
     */
    @Override
    public boolean validateObject(GrpcConnKey key, PooledObject<ManagedChannel> p) {
        return !p.getObject().isShutdown();
    }

    static class PooledManagedChannel extends DefaultPooledObject<ManagedChannel> {

        /**
         * Creates a new instance that wraps the provided object so that the pool can
         * track the state of the pooled object.
         *
         * @param object The object to wrap
         */
        private PooledManagedChannel(ManagedChannel object) {
            super(object);
        }
    }
}
