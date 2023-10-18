package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.SerieDocumental;
import com.sistema_expedientes.entities.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;
import com.sistema_expedientes.services.exceptions.SerieDocumentalNoEncontradaExcepcion;

import java.util.List;

public interface ISerieDocumentalRepositorioDTO {

    public SerieDocumental get(Short id) throws SerieDocumentalNoEncontradaExcepcion;

    public List<SerieDocumental> list();

    public SerieDocumental create(SerieDocumentalRequestDTO request) throws Exception;

    public SerieDocumental put(Short id, SerieDocumentalRequestDTO request);

}
