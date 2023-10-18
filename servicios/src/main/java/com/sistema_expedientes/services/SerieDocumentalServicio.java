package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.SerieDocumental;
import com.sistema_expedientes.entities.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.repositories.SerieDocumentalRepositorio;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;
import com.sistema_expedientes.services.exceptions.SerieDocumentalNoEncontradaExcepcion;
import com.sistema_expedientes.services.interfaces.ISerieDocumentalRepositorioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieDocumentalServicio implements ISerieDocumentalRepositorioDTO {

    @Autowired
    private SerieDocumentalRepositorio repositorio;

    @Autowired
    private SeccionServicio seccionServicio;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<SerieDocumental> list() {
        return repositorio.findAll();
    }

    @Override
    public SerieDocumental get(Short id) throws SerieDocumentalNoEncontradaExcepcion {
        Optional<SerieDocumental> in_bd = repositorio.findById(id);
        if(in_bd.isEmpty())
            throw new SerieDocumentalNoEncontradaExcepcion(id);
        return in_bd.get();
    }

    @Override
    public SerieDocumental create(SerieDocumentalRequestDTO request) throws SerieDocumentalNoEncontradaExcepcion, SeccionNoEncontradaException {

        SerieDocumental to_bd = dtoToEntity(request);
        if (request.getSerie_padre() > 0)
            to_bd.setSeriePadre(get(request.getSerie_padre()));

        if (request.getSeccion() != null)
            to_bd.setSeccion(seccionServicio.get(request.getSeccion()));

        if(request.getObservaciones() == null)
            to_bd.setObservaciones("");

        return repositorio.save(to_bd);

    }

    @Override
    public SerieDocumental put(Short id, SerieDocumentalRequestDTO request) {
        return null;
    }

    private SerieDocumental dtoToEntity(SerieDocumentalRequestDTO dto){
        return this.mapper.map(dto, SerieDocumental.class);
    }

}