package com.sistema_expedientes.services.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String msg){
        super(msg);
    }
}
