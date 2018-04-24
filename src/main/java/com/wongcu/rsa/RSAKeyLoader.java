package com.wongcu.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface RSAKeyLoader {

    PublicKey loadPublicKey();

    PrivateKey loadPrivateKey();
}
