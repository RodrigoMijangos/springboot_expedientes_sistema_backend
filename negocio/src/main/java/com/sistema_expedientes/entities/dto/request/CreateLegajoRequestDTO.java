package com.sistema_expedientes.entities.dto.request;

import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;

public class CreateLegajoRequestDTO extends LegajoRequestDTO{

    private ExpedienteCompositeKey expediente;

    public CreateLegajoRequestDTO() {
    }

    public CreateLegajoRequestDTO(String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria, ExpedienteCompositeKey expediente) {
        super(numeroMueble, letraEstante, numeroPasillo, letraBateria);
        this.expediente = expediente;
    }

    public ExpedienteCompositeKey getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteCompositeKey expediente) {
        this.expediente = expediente;
    }
}
