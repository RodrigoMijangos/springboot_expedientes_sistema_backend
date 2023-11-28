package com.sistema_expedientes.services.documento;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.documento.dto.request.base.DocumentoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentoServicioMetodos {

    public List<Documento> getAll();
    public Documento get(Long id) throws Exception;
    Documento create(DocumentoRequest request, MultipartFile file, String folderId) throws IOException;

    public Documento put(DocumentoRequest request, Long id) throws Exception;
    public void delete(Long id) throws Exception;

}
