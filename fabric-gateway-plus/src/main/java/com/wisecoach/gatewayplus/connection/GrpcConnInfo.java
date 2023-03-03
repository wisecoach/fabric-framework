package com.wisecoach.gatewayplus.connection;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午9:41
 * {@code @version:} 1.0.0
 */


public class GrpcConnInfo {

    private final String endpoint;
    private final X509Certificate tlsCert;
    private final String authority;

    public GrpcConnInfo(String endpoint, X509Certificate tlsCert, String authority) {
        this.endpoint = endpoint;
        this.tlsCert = tlsCert;
        this.authority = authority;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public X509Certificate getTlsCert() {
        return tlsCert;
    }

    public String getAuthority() {
        return authority;
    }
}
