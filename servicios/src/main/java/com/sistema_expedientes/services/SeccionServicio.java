package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Seccion;
import com.sistema_expedientes.entities.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.repositories.SeccionRepositorio;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;
import com.sistema_expedientes.services.interfaces.SeccionServicioMetodos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionServicio implements SeccionServicioMetodos {

    @Autowired
    private SeccionRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Seccion> getAll() {
        return repositorio.findAll();
    }

    @Override
    public Seccion get(String clave) throws SeccionNoEncontradaException {
        Optional<Seccion> in_bd = repositorio.findById(clave);

        if(in_bd.isEmpty())
            throw new SeccionNoEncontradaException("No se ha encontrado la sección con la clave " + clave);

        return in_bd.get();
    }

    @Override
    public Seccion create(SeccionRequestDTO request) {
        Seccion to_bd = dtoToEntity(request);
        return repositorio.save(to_bd);
    }

    @Override
    public Seccion put(String clave, SeccionRequestDTO request) throws SeccionNoEncontradaException {

        Seccion in_bd = get(clave);

        in_bd.setNombre(request.getNombre());
        in_bd.setDescripcion(request.getDescripcion());

        return repositorio.save(in_bd);
    }

    @Override
    public int delete(String clave) throws SeccionNoEncontradaException {
        Seccion in_bd = get(clave);
        repositorio.delete(in_bd);
        return 1;
    }

    private Seccion dtoToEntity(SeccionRequestDTO dto){
        return this.mapper.map(dto, Seccion.class);
    }

}
