package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.SerieDocumental;
import com.sistema_expedientes.entities.dto.request.SerieDocumentalRequestDTO;

import java.util.List;

public interface ISerieDocumentalRepositorioDTO {

    public SerieDocumental get(Short id);

    public List<SerieDocumental> list();

    public SerieDocumental create(SerieDocumentalRequestDTO request);

    public SerieDocumental put(Short id, SerieDocumentalRequestDTO request);

}
