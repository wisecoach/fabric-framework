package com.wisecoach.gatewayplus.connection;

/**
 * 用于支持不同等级的grpc连接共用方案
 * Note:需要重写equals方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午9:20
 * {@code @version:} 1.0.0
 */

public interface GrpcConnKey {
    String getKey();
}
