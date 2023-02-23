package com.wisecoach.security.user;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * 用于该框架的用户信息，会包含用户的私钥、证书、mspID等信息
 * @author: wisecoach
 * @date: 2023/2/23 上午10:26
 * @version: 1.0.0
 */

public interface User {

    /**
     * 取得当前用户的mspID
     * @return mspID
     */
    String getMspId();

    /**
     * 取得当前用户的私钥
     * @return privateKey
     */
    PrivateKey getPrivateKey();

    /**
     * 取得当前用户的证书
     * @return certificate
     */
    Certificate getCertificate();
}
