package com.sistema_expedientes.services.legajo;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateLegajoRequestDTO;
import com.sistema_expedientes.entities.dto.request.LegajoRequestDTO;
import com.sistema_expedientes.entities.dto.request.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTLegajoRequestDTO;

import java.util.List;

public interface LegajoServicioMetodos {

    public Legajo get(LegajoCompositeKey id) throws Exception;
    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey expedienteCompositeKey);
    public Legajo create(CreateLegajoRequestDTO request) throws Exception;
    public Legajo put(PUTLegajoRequestDTO request) throws Exception;
    public void delete(LegajoCompositeKey id) throws Exception;
    public Legajo guardarListaDocumentos(ListaDocumentosLegajoRequestDTO request) throws Exception;

}
