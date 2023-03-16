package com.wisecoach.gatewayplus.connection;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import org.apache.commons.pool2.KeyedObjectPool;

import java.util.concurrent.TimeUnit;

/**
 * MangedChannel的装饰器，主要对其shutdown方法提供了返回池子的功能
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/16 下午10:10
 * {@code @version:} 1.0.0
 */


public class PooledManagedChannel extends ManagedChannel {

    private final GrpcConnKey key;
    private final ManagedChannel channel;
    private final KeyedObjectPool<GrpcConnKey, ManagedChannel> pool;

    public PooledManagedChannel(GrpcConnKey key, ManagedChannel channel, KeyedObjectPool<GrpcConnKey, ManagedChannel> pool) {
        this.key = key;
        this.channel = channel;
        this.pool = pool;
    }

    /**
     * 不是关闭连接，而是放回池子
     */
    @Override
    public ManagedChannel shutdown() {
        try {
            pool.returnObject(key, channel);
            return channel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isShutdown() {
        return channel.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return channel.isTerminated();
    }

    /**
     * shutdownNow，主要还有一个重要的作用就是强行停止发送的功能，不适合再放回池子了
     */
    @Override
    public ManagedChannel shutdownNow() {
        return channel.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return channel.awaitTermination(timeout, unit);
    }

    @Override
    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        return channel.newCall(methodDescriptor, callOptions);
    }

    @Override
    public String authority() {
        return null;
    }
}
