package com.sistema_expedientes.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtility {

    public static KeyPair generateRsaKey(){

        KeyPair keyPair;
        KeyPairGenerator keyPairGenerator = null;

        try{
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        }catch (NoSuchAlgorithmException e){
            throw  new RuntimeException(e);
        }

        keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }
}
