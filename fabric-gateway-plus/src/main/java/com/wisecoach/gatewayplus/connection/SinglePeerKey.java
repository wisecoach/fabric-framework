package com.wisecoach.gatewayplus.connection;

/**
 * 该key用于实现所有client访问同一个peer节点的方案
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午5:02
 * {@code @version:} 1.0.0
 */


public class SinglePeerKey implements GrpcConnKey {

    private final static String singlePeerKey = "singlePeer";

    public final static SinglePeerKey INSTANCE = new SinglePeerKey();

    @Override
    public String getKey() {
        return singlePeerKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MspKey) {
            return this.getKey().equals(((MspKey) obj).getKey());
        }
        return false;
    }
}
