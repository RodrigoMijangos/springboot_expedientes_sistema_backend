package com.sistema_expedientes.entities.enumerates;

public enum TradicionDocumentalExpediente {
    ORIGINAL((byte) 1), COPIAS((byte)2), AMBOS((byte)3);

    private final byte codigo;

    private TradicionDocumentalExpediente(byte codigo){
        this.codigo = codigo;
    }

    public byte getCodigo() {
        return codigo;
    }

    public static TradicionDocumentalExpediente valueOf(byte codigo){
        for(TradicionDocumentalExpediente tradicionDocumentalExpediente : values()){
            if(tradicionDocumentalExpediente.getCodigo() == codigo)
                return tradicionDocumentalExpediente;
        }

        return null;
    }
}
