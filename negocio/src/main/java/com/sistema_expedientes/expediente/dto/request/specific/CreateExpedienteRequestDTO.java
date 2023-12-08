package com.sistema_expedientes.expediente.dto.request.specific;

import com.sistema_expedientes.expediente.dto.request.base.ExpedienteRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateExpedienteRequestDTO extends ExpedienteRequest {

    @Min(1)
    @Max(Short.MAX_VALUE)
    private Short identificadorSerieDocumental;
    @NotBlank
    private String unidadAdministrativaGeneradora;
    @NotNull
    private LocalDate fechaApertura;
    public CreateExpedienteRequestDTO() {
    }

    public CreateExpedienteRequestDTO(Short periodoCierre, String asunto, String tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, Byte formatoExpediente, Byte condicionAcceso, Byte tradicionDocumental, Byte tipoInformacion, Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, LocalDate fechaApertura) {
        super(periodoCierre, asunto, tipoExpediente, numeroProyecto, nombreProyecto, acronimoInstitucion, nombreInstitucion, numeroContacto, cantidadHojas, formatoExpediente, condicionAcceso, tradicionDocumental, tipoInformacion);
        this.identificadorSerieDocumental = identificadorSerieDocumental;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
        this.fechaApertura = fechaApertura;
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

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
