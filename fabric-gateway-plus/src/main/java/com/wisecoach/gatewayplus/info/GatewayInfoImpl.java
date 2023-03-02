package com.wisecoach.gatewayplus.info;

import org.hyperledger.fabric.client.identity.Signer;

import java.security.cert.Certificate;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:45
 * {@code @version:} 1.0.0
 */


public class GatewayInfoImpl implements GatewayInfo {

    private final String mspId;
    private final Signer signer;
    private final Certificate certificate;

    public GatewayInfoImpl(String mspId, Signer signer, Certificate certificate) {
        this.mspId = mspId;
        this.signer = signer;
        this.certificate = certificate;
    }

    @Override
    public String getMspId() {
        return mspId;
    }

    @Override
    public Signer getSigner() {
        return signer;
    }

    @Override
    public Certificate getCertificate() {
        return certificate;
    }
}
