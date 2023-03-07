package com.wisecoach.gatewayplus.connection;

import org.hyperledger.fabric.client.identity.Identities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 就是GrpcConnInfo把Cert换成Path
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:36
 * {@code @version:} 1.0.0
 */


public class RawConnInfo {
    private String endpoint;
    private String tlsCertPath;
    private String authority;

    public RawConnInfo() {
    }

    public RawConnInfo(String endpoint, String tlsCertPath, String authority) {
        this.endpoint = endpoint;
        this.tlsCertPath = tlsCertPath;
        this.authority = authority;
    }

    public GrpcConnInfo form() {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(tlsCertPath), StandardCharsets.UTF_8);
            X509Certificate certificate = Identities.readX509Certificate(reader);
            return new GrpcConnInfo(endpoint, certificate, authority);
        } catch (IOException | CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTlsCertPath() {
        return tlsCertPath;
    }

    public void setTlsCertPath(String tlsCertPath) {
        this.tlsCertPath = tlsCertPath;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
