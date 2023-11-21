package com.sistema_expedientes.expediente.dto.request.specific;

import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;

import java.util.List;

public class ListaLegajosExpedienteRequestDTO {

    private ExpedienteCompositeKey expediente;
    private List<CreateLegajoRequestDTO> legajos;

    public ListaLegajosExpedienteRequestDTO() {
    }

    public ListaLegajosExpedienteRequestDTO(ExpedienteCompositeKey expediente, List<CreateLegajoRequestDTO> legajos) {
        this.expediente = expediente;
        this.legajos = legajos;
    }

    public ExpedienteCompositeKey getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteCompositeKey expediente) {
        this.expediente = expediente;
    }

    public List<CreateLegajoRequestDTO> getLegajos() {
        return legajos;
    }

    public void setLegajos(List<CreateLegajoRequestDTO> legajos) {
        this.legajos = legajos;
    }
}
