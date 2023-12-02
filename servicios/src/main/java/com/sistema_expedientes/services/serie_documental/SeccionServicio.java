package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.seccion.Seccion;
import com.sistema_expedientes.serie_documental.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.serie_documental.seccion.SeccionRepositorio;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionServicio implements SeccionServicioMetodos {

    private final SeccionRepositorio repositorio;
    private final ModelMapper mapper;

    public SeccionServicio(SeccionRepositorio repositorio, ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public List<Seccion> getAll() {
        return repositorio.findAll();
    }

    @Override
    public Seccion get(String clave) throws ResourceNotFoundException {
        return repositorio.findById(clave).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("La sección no está disponible o no existe")
        ));
    }

    @Override
    public Seccion create(SeccionRequestDTO request) {
        Seccion to_bd = dtoToEntity(request);
        return repositorio.save(to_bd);
    }

    @Override
    public Seccion put(String clave, SeccionRequestDTO request) throws ResourceNotFoundException {

        Seccion in_bd = get(clave);

        in_bd.setNombre(request.getNombre());
        in_bd.setDescripcion(request.getDescripcion());

        return repositorio.save(in_bd);
    }

    @Override
    public int delete(String clave) throws ResourceNotFoundException {
        Seccion in_bd = get(clave);
        repositorio.delete(in_bd);
        return 1;
    }

    private Seccion dtoToEntity(SeccionRequestDTO dto){
        return this.mapper.map(dto, Seccion.class);
    }

}
