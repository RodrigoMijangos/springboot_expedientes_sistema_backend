package com.sistema_expedientes.services.Exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{
    public UsernameAlreadyTakenException(String msg){
        super(msg);
    }
}
