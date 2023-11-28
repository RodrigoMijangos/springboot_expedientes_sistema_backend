package com.sistema_expedientes.expediente.enumerates;

import java.util.stream.Stream;

public enum TipoInformacionExpediente {
    PUBLICA((byte) 1), PARCIALMENTE_RESERVADA((byte)2), TOTALMENTE_RESERVADA((byte)3);

    private final Byte codigo;

    private TipoInformacionExpediente(Byte codigo){
        this.codigo = codigo;
    }

    public Byte getCodigo() {
        return codigo;
    }

    public static TipoInformacionExpediente valueOf(Byte codigo){
        return Stream.of(values()).filter(
                tipoInformacionExpediente -> tipoInformacionExpediente.getCodigo().equals(codigo)
                ).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
