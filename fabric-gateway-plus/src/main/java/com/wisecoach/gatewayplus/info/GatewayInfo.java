package com.wisecoach.gatewayplus.info;

import org.hyperledger.fabric.client.identity.Signer;

import java.security.cert.X509Certificate;

/**
 * 不装了，就是 另一个框架的 User，我只是为了拆开（为什么呢？）才搞了一样的接口换个名字
 * 不过作用还是不一样的，这个是为了对外提供接口来注入获取Gateway所需的信息
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:29
 * {@code @version:} 1.0.0
 */

public interface GatewayInfo {
    /**
     * 取得gateway的mspID
     * @return mspID
     */
    String getMspId();

    /**
     * 取得gateway所需的signer，这里为signer主要是为了支持自定义签名方式
     * @return signer
     */
    Signer getSigner();

    /**
     * 取得gateway所需的证书
     * @return certificate
     */
    X509Certificate getCertificate();
}
