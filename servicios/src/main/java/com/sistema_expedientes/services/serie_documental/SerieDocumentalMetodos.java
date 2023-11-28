package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.serie_documental.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.services.exceptions.SerieDocumentalNoEncontradaExcepcion;

import java.util.List;

public interface SerieDocumentalMetodos {

    public SerieDocumental get(Short id) throws SerieDocumentalNoEncontradaExcepcion;

    public List<SerieDocumental> list();

    public SerieDocumental create(SerieDocumentalRequestDTO request) throws Exception;

    public SerieDocumental put(Short id, SerieDocumentalRequestDTO request);

}
