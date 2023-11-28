package com.sistema_expedientes.expediente.enumerates;

import java.util.stream.Stream;

public enum TradicionDocumentalExpediente {
    ORIGINAL((byte) 1), COPIAS((byte)2), AMBOS((byte)3);

    private final Byte codigo;

    private TradicionDocumentalExpediente(Byte codigo){
        this.codigo = codigo;
    }

    public Byte getCodigo() {
        return codigo;
    }

    public static TradicionDocumentalExpediente valueOf(Byte codigo){
        return Stream.of(values())
                .filter(
                        tradicionDocumentalExpediente -> tradicionDocumentalExpediente.getCodigo().equals(codigo)
                ).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
