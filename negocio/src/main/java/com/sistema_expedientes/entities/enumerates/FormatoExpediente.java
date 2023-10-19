package com.sistema_expedientes.entities.enumerates;

import java.util.stream.Stream;

public enum FormatoExpediente {
    FISICO((byte)1), ELECTRONICO((byte)2), AMBOS((byte)3);
    private final Byte codigo;
    private FormatoExpediente(Byte id){
        this.codigo = id;
    }

    public Byte getCodigo() {
        return codigo;
    }

    public static FormatoExpediente valueOf(Byte id){
        return Stream.of(values()).filter(formatoExpediente -> formatoExpediente.getCodigo().equals(id))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
