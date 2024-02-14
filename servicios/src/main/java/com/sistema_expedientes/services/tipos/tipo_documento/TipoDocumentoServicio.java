package com.sistema_expedientes.services.tipos.tipo_documento;

import com.sistema_expedientes.tipos.tipo_documento.TipoDocumento;
import com.sistema_expedientes.tipos.tipo_documento.TipoDocumentoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServicio {

    private final TipoDocumentoRepositorio repositorio;

    public TipoDocumentoServicio(TipoDocumentoRepositorio repositorio){
        this.repositorio = repositorio;
    }

}
