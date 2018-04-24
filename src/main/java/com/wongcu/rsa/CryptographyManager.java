package com.wongcu.rsa;

/**
 * @author WongCU
 */
public interface CryptographyManager {
    /**
     * 加密
     *
     * @param plainText
     * @return
     */
    String encrypt(String plainText) throws Exception;

    /**
     * 解密
     *
     * @param cipherText
     * @return
     */
    String decrypt(String cipherText) throws Exception;
}
