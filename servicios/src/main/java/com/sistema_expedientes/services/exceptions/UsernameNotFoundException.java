package com.sistema_expedientes.services.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
