package com.sistema_expedientes.legajo.dto.request.specific;

import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.legajo.dto.request.base.LegajoRequestDTO;
import jakarta.validation.Valid;

public class CreateLegajoRequestDTO extends LegajoRequestDTO {

    @Valid
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
