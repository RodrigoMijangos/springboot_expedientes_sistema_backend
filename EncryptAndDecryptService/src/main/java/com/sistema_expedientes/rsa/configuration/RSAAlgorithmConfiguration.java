package com.sistema_expedientes.rsa.configuration;

import com.sistema_expedientes.rsa.service.encode.Encoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@EnableConfigurationProperties({RSAServiceProperties.class, DataToEncryptParameters.class})
public class RSAAlgorithmConfiguration implements Encoder {

    private final RSAServiceProperties serviceProperties;

    public RSAAlgorithmConfiguration(RSAServiceProperties serviceProperties){
        this.serviceProperties = serviceProperties;
    }

    @Bean
    public KeyPair keyPairGeneration() throws Exception {
        if(serviceProperties.privateKey() != null && serviceProperties.publicKey() != null){
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decode(serviceProperties.publicKey()));
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decode(serviceProperties.privateKey()));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return new KeyPair(
                    keyFactory.generatePublic(publicKeySpec),
                    keyFactory.generatePrivate(privateKeySpec)
            );

        }else{
            throw new Exception("No se puede chabo");
        }
    }

    @Bean("cipherEncrypt")
    public Cipher cipherEncrypt(KeyPair keyPair) throws GeneralSecurityException {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        return cipher;
    }

    @Bean("cipherDecrypt")
    public Cipher cipherDecrypt(KeyPair keyPair) throws GeneralSecurityException {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return cipher;
    }

    private Cipher getCipherInstance() throws GeneralSecurityException {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

}
