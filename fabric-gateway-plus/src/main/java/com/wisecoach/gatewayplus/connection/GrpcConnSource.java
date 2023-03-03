package com.wisecoach.gatewayplus.connection;

import io.grpc.ManagedChannel;

import java.security.cert.X509Certificate;

/**
 * GRPC连接数据源，允许按照{@link GrpcConnKey}获取连接，根绝Key的不同实现，可以实现不同级别的GRPC连接管理
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午9:18
 * {@code @version:} 1.0.0
 */

public interface GrpcConnSource {

    /**
     * 根据key取得连接
     * @param key grpcConnKey
     * @return grpcConnection
     */
    ManagedChannel getConnection(GrpcConnKey key);

    /**
     * 根据key注册{@link GrpcConnInfo}，后续可以根据信息生成对应的连接
     * @param key grpcConnKey
     * @param endpoint 要连接的端点
     */
    void register(GrpcConnKey key, String endpoint);

    /**
     * 根据key注册{@link GrpcConnInfo}，后续可以根据信息生成对应的连接
     * @param key grpcConnKey
     * @param endpoint 要连接的端点
     * @param authority 要求的host，如果和端点的host不同会报错
     */
    void register(GrpcConnKey key, String endpoint, String authority);

    /**
     * 根据key注册{@link GrpcConnInfo}，后续可以根据信息生成对应的连接
     * @param key grpcConnKey
     * @param endpoint 要连接的端点
     * @param tlsCert tls证书，不设置就采用grpc而不是grpcs
     */
    void register(GrpcConnKey key, String endpoint, X509Certificate tlsCert);

    /**
     * 根据key注册{@link GrpcConnInfo}，后续可以根据信息生成对应的连接
     * @param key grpcConnKey
     * @param endpoint 要连接的端点
     * @param tlsCert tls证书，不设置就采用grpc而不是grpcs
     * @param authority 要求的host，如果和端点的host不同会报错
     */
    void register(GrpcConnKey key, String endpoint, X509Certificate tlsCert, String authority);

}
