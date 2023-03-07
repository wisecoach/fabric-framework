package com.wisecoach.fabric.security;

import com.wisecoach.security.user.User;
import com.wisecoach.security.user.UserImpl;
import com.wisecoach.security.user.UserProvider;
import com.wisecoach.security.userinfo.UserInfo;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Signer;
import org.hyperledger.fabric.client.identity.Signers;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/28 下午8:52
 * {@code @version:} 1.0.0
 */

public class CustomUserProvider2 implements UserProvider {

    private final static String pvtPath = "/mnt/F/fabric-samples/fabric-samples-2.4.8/fabric-samples/test-network/" +
            "organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/priv_key";

    private final static String signerCert = "/mnt/F/fabric-samples/fabric-samples-2.4.8/fabric-samples/test-network" +
            "/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/signcerts/cert.pem";

    @Override
    public User getUser(UserInfo userInfo) {
        Signer signer = null;
        X509Certificate certificate = null;
        try {
            BufferedReader privateKeyReader = Files.newBufferedReader(Paths.get(pvtPath), StandardCharsets.UTF_8);
            PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);
            signer = Signers.newPrivateKeySigner(privateKey);
            BufferedReader certReader = Files.newBufferedReader(Paths.get(signerCert), StandardCharsets.UTF_8);
            certificate = Identities.readX509Certificate(certReader);
        } catch (IOException | InvalidKeyException | CertificateException e) {
            throw new RuntimeException(e);
        }
        return new UserImpl("Org1MSP", signer, certificate);
    }

    @Override
    public boolean support(UserInfo userInfo) {
        return userInfo instanceof UserInfoImpl;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
