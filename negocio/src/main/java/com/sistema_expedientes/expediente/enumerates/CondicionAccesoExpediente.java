package com.sistema_expedientes.expediente.enumerates;

import java.util.stream.Stream;

public enum CondicionAccesoExpediente {
    BUENA((byte)3), REGULAR((byte)2), MALA((byte)1);

    private final Byte codigo;

    private CondicionAccesoExpediente(Byte codigo){
        this.codigo = codigo;
    }

    public Byte getCodigo() {
        return this.codigo;
    }

    public static CondicionAccesoExpediente valueOf(Byte codigo){
        return Stream.of(values()).filter(
                condicionAccesoExpediente -> condicionAccesoExpediente.getCodigo().equals(codigo)
        ).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
