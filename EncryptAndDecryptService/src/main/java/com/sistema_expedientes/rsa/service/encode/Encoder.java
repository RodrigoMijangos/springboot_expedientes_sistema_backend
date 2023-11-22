package com.sistema_expedientes.rsa.service.encode;

import java.util.Base64;

public interface Encoder {

    default byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    default String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

}
