package com.sistema_expedientes.services.exceptions;

import java.util.function.Supplier;

public class ResourceNotFoundException extends Exception {

    //public ResourceNotFoundException(String message) { super(message) };

    public ResourceNotFoundException(Throwable cause) {
        super("Recurso no encontrado", cause);
    }
}
