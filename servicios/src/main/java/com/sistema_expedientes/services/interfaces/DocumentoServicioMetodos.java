package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.dto.request.DocumentoRequest;

import java.util.List;

public interface DocumentoServicioMetodos {

    public List<Documento> getAll();
    public Documento get(Long id) throws Exception;
    public Documento create(DocumentoRequest request) throws Exception;
    public Documento put(DocumentoRequest request, Long id) throws Exception;
    public void delete(Long id) throws Exception;

}
