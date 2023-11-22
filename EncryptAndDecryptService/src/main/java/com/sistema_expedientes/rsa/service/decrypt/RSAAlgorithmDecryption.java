package com.sistema_expedientes.rsa.service.decrypt;

import com.sistema_expedientes.rsa.service.encode.Encoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

@Service
public class RSAAlgorithmDecryption implements Encoder {

    private final Cipher cipher;

    public RSAAlgorithmDecryption(@Qualifier("cipherDecrypt") Cipher cipher){
        this.cipher = cipher;
    }

    public String decrypt(String encryptedData) throws GeneralSecurityException {
        return new String(
                cipher.doFinal(decode(encryptedData)),
                StandardCharsets.UTF_8
        );
    }

}
