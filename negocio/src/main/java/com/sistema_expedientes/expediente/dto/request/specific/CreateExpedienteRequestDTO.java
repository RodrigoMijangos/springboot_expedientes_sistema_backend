package com.sistema_expedientes.expediente.dto.request.specific;

import com.sistema_expedientes.expediente.dto.request.base.ExpedienteRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateExpedienteRequestDTO extends ExpedienteRequest {

    @NotNull
    private Short identificadorSerieDocumental;
    @NotNull
    @NotBlank
    private String unidadAdministrativaGeneradora;

    public CreateExpedienteRequestDTO() {
    }

    public CreateExpedienteRequestDTO(Short periodoCierre, String asunto, String tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, Byte formatoExpediente, Byte condicionAcceso, Byte tradicionDocumental, Byte tipoInformacion, Short identificadorSerieDocumental, String unidadAdministrativaGeneradora) {
        super(periodoCierre, asunto, tipoExpediente, numeroProyecto, nombreProyecto, acronimoInstitucion, nombreInstitucion, numeroContacto, cantidadHojas, formatoExpediente, condicionAcceso, tradicionDocumental, tipoInformacion);
        this.identificadorSerieDocumental = identificadorSerieDocumental;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
    }

    public Short getIdentificadorSerieDocumental() {
        return identificadorSerieDocumental;
    }

    public void setIdentificadorSerieDocumental(Short identificadorSerieDocumental) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
    }

    public String getUnidadAdministrativaGeneradora() {
        return unidadAdministrativaGeneradora;
    }

    public void setUnidadAdministrativaGeneradora(String unidadAdministrativaGeneradora) {
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
    }
}
