package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.serie_documental.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.serie_documental.SerieDocumentalRepositorio;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieDocumentalServicio implements SerieDocumentalMetodos {

    private final SerieDocumentalRepositorio repositorio;
    private final SeccionServicio seccionServicio;
    private final ModelMapper mapper;

    public SerieDocumentalServicio(SerieDocumentalRepositorio repositorio, SeccionServicio seccionServicio, ModelMapper mapper) {
        this.repositorio = repositorio;
        this.seccionServicio = seccionServicio;
        this.mapper = mapper;
    }

    @Override
    public List<SerieDocumental> list() {
        return repositorio.findAll();
    }

    public List<SerieDocumental> listarSoloVigentes(){
        return this.repositorio.findAllByVigenteIsTrue();
    }

    @Override
    public SerieDocumental get(Short id) throws ResourceNotFoundException {
        return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("La serie documental no está disponible o no existe")
        ));
    }

    public SerieDocumental getSerieDocumentalVigente(Short identificador) throws ResourceNotFoundException{
        return this.repositorio.findByIdentificadorAndVigenteIsTrue(identificador).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("La serie documental no es vigente o no existe")
        ));
    }

    @Override
    public SerieDocumental create(SerieDocumentalRequestDTO request) throws ResourceNotFoundException {

        SerieDocumental to_bd = dtoToEntity(request);
        if (request.getSerie_padre() != null && request.getSerie_padre() != 0)
            to_bd.setSeriePadre(
                    getSerieDocumentalVigente(request.getSerie_padre())
            );

        if (request.getSeccion() != null)
            to_bd.setSeccion(
                    seccionServicio.getSeccionVigente(request.getSeccion())
            );

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