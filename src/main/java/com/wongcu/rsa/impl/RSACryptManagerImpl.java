package com.wongcu.rsa.impl;

import com.wongcu.rsa.CryptographyManager;
import com.wongcu.rsa.RSAKeyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 * @author WongCU
 */
@Component
public class RSACryptManagerImpl implements CryptographyManager {

    @Autowired
    private RSAKeyLoader rsaKeyLoader;

    private Cipher encryptCipher;

    private Cipher decryptCipher;

    @PostConstruct
    public void init() throws Exception {
        encryptCipher = Cipher.getInstance("RSA");
        decryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, rsaKeyLoader.loadPublicKey());
        decryptCipher.init(Cipher.DECRYPT_MODE, rsaKeyLoader.loadPrivateKey());
    }


    /**
     * 加密過程 String->byte[明文]-加密->byte[密文]-Base64->byte[Base64]->String
     * @param plainText
     * @return
     * @throws Exception
     */
    @Override
    public String encrypt(String plainText) throws Exception {
        return new String(encrypt(plainText.getBytes()));
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        return new String(decrypt(cipherText.getBytes()));
    }

    public byte[] encrypt(byte[] bytes) throws Exception {
        byte[] encryptByte = encryptCipher.doFinal(bytes);
        return Base64.getEncoder().encode(encryptByte);
    }

    public byte[] decrypt(byte[] bytes) throws Exception {
        byte[] decodeByte = Base64.getDecoder().decode(bytes);
        return decryptCipher.doFinal(decodeByte);
    }
}
