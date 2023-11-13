package com.sistema_expedientes.entities.dto.request;

import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;

public class PUTLegajoRequestDTO extends LegajoRequestDTO{

    private LegajoCompositeKey id;

    public PUTLegajoRequestDTO() {
    }

    public PUTLegajoRequestDTO(String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria, LegajoCompositeKey id) {
        super(numeroMueble, letraEstante, numeroPasillo, letraBateria);
        this.id = id;
    }

    public LegajoCompositeKey getId() {
        return id;
    }

    public void setId(LegajoCompositeKey id) {
        this.id = id;
    }
}
