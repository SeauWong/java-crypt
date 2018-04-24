package com.wongcu;

import com.wongcu.rsa.CryptographyManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaCryptApplicationTests {

	@Autowired
	private CryptographyManager cryptographyManager;

	private static final String PLAIN_TEST = "Oh My God!!";

	@Test
	public void testEncrypt() throws Exception {
		String encrypt = cryptographyManager.encrypt(PLAIN_TEST);
		System.out.println("密文:" + encrypt);
		Assert.hasText(encrypt,"加密失败");
	}

	@Test
	public void testDecrypt() throws Exception {
		String encrypt = cryptographyManager.encrypt(PLAIN_TEST);
		Assert.hasText(encrypt,"加密失败");
		String decrypt = cryptographyManager.decrypt(encrypt);
		System.out.println("明文：" + decrypt);
		Assert.isTrue(PLAIN_TEST.equals(decrypt),"解密失败");
	}

}
