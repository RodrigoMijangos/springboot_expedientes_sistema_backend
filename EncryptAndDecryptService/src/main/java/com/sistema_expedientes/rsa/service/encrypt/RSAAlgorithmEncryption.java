package com.sistema_expedientes.rsa.service.encrypt;

import com.sistema_expedientes.rsa.service.encode.Encoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;

@Service
public class RSAAlgorithmEncryption implements Encoder {

    private final Cipher cipher;

    public RSAAlgorithmEncryption(@Qualifier("cipherEncrypt") Cipher cipher) {
        this.cipher = cipher;
    }

    public String encrypt(String data) throws GeneralSecurityException {
        return encode(
                cipher.doFinal(data.getBytes())
        );

    }

}
