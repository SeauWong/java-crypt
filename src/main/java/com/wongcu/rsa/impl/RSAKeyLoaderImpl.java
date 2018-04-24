package com.wongcu.rsa.impl;

import com.wongcu.rsa.RSAKeyLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author WongCU
 */
@Configuration
public class RSAKeyLoaderImpl implements RSAKeyLoader {

    @Value("${rsa.publicKey}")
    private String publicKeyResource;

    @Value("${rsa.privateKey}")
    private String privateKeyResource;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyResource.getBytes()));
        PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyResource.getBytes()));
        publicKey = keyFactory.generatePublic(x509);
        privateKey = keyFactory.generatePrivate(pkcs8);
    }

    @Override
    public PublicKey loadPublicKey() {
        return publicKey;
    }

    @Override
    public PrivateKey loadPrivateKey() {
        return privateKey;
    }
}
