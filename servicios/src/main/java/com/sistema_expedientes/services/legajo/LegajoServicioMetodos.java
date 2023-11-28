package com.sistema_expedientes.services.legajo;

import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LegajoServicioMetodos {

    public Legajo get(LegajoCompositeKey id) throws Exception;
    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey expedienteCompositeKey);
    public Legajo create(CreateLegajoRequestDTO request) throws Exception;
    public Legajo put(PUTLegajoRequestDTO request) throws Exception;
    public void delete(LegajoCompositeKey id) throws Exception;

    Legajo guardarListaDocumentos(ListaDocumentosLegajoRequestDTO request, MultipartFile[] files) throws Exception;
}
