package com.sistema_expedientes.services.documento.mapeo;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.dto.request.DocumentoRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapeoDocumentoServicio {

    @Autowired
    private ModelMapper mapper;

    public Documento dtoToEntity(DocumentoRequest dto){
        return obtenerOCrearMapeoDocumentoRequest().map(dto);
    }

    private TypeMap<DocumentoRequest, Documento> obtenerOCrearMapeoDocumentoRequest(){
        TypeMap<DocumentoRequest, Documento> typeMap = this.mapper.getTypeMap(DocumentoRequest.class, Documento.class);
        if(typeMap == null){
            return this.mapper.createTypeMap(DocumentoRequest.class, Documento.class)
                    .addMappings(mapping -> {
                        mapping.map(DocumentoRequest::getNombre, Documento::setNombre);
                    });
        }

        return typeMap;
    }

}
