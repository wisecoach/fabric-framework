package com.wisecoach.gatewayplus.connection;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午9:47
 * {@code @version:} 1.0.0
 */


public class MspKey implements GrpcConnKey {

    private final static String prefix = "MSPID:";

    private final String mspId;

    public MspKey(String mspId) {
        this.mspId = mspId;
    }

    @Override
    public String getKey() {
        return prefix + mspId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MspKey) {
            return this.getKey().equals(((MspKey) obj).getKey());
        }
        return false;
    }
}
