package com.wisecoach.security.user;

import org.hyperledger.fabric.client.identity.Signer;

import java.security.cert.Certificate;

/**
 * 该框架的 {@link User} 的默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午10:31
 * {@code @version:} 1.0.0
 */


public class UserImpl implements User {

    private final String mspId;
    private final Signer signer;
    private final Certificate certificate;

    public UserImpl(String mspId, Signer signer, Certificate certificate) {
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
