package com.wisecoach.security.user;

import org.hyperledger.fabric.client.identity.Signer;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * 用于该框架的用户信息，会包含用户的signer、证书、mspID等信息
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午10:26
 * {@code @version:} 1.0.0
 */

public interface User {

    /**
     * 取得当前用户的mspID
     * @return mspID
     */
    String getMspId();

    /**
     * 取得当前用户的signer，这里为signer主要是为了支持自定义签名方式
     * @return signer
     */
    Signer getSigner();

    /**
     * 取得当前用户的证书
     * @return certificate
     */
    X509Certificate getCertificate();
}
