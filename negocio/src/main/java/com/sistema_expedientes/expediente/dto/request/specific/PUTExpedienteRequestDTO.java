package com.sistema_expedientes.expediente.dto.request.specific;

import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.dto.request.base.ExpedienteRequest;

public class PUTExpedienteRequestDTO extends ExpedienteRequest {

    private ExpedienteCompositeKey id;

    public PUTExpedienteRequestDTO() {
    }

    public PUTExpedienteRequestDTO(Short periodoCierre, String asunto, String tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, Byte formatoExpediente, Byte condicionAcceso, Byte tradicionDocumental, Byte tipoInformacion, ExpedienteCompositeKey id) {
        super(periodoCierre, asunto, tipoExpediente, numeroProyecto, nombreProyecto, acronimoInstitucion, nombreInstitucion, numeroContacto, cantidadHojas, formatoExpediente, condicionAcceso, tradicionDocumental, tipoInformacion);
        this.id = id;
    }

    public ExpedienteCompositeKey getId() {
        return id;
    }

    public void setId(ExpedienteCompositeKey id) {
        this.id = id;
    }
}
