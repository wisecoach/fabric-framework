package com.wisecoach.security.user;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * 该框架的 {@link User} 的默认实现
 * @author: wisecoach
 * @date: 2023/2/23 上午10:31
 * @version: 1.0.0
 */


public class UserImpl implements User {

    private final String mspId;
    private final PrivateKey privateKey;
    private final Certificate certificate;

    public UserImpl(String mspId, PrivateKey privateKey, Certificate certificate) {
        this.mspId = mspId;
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    @Override
    public String getMspId() {
        return mspId;
    }

    @Override
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public Certificate getCertificate() {
        return certificate;
    }
}
