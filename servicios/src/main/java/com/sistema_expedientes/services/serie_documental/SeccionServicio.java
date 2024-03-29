package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.seccion.Seccion;
import com.sistema_expedientes.serie_documental.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.serie_documental.seccion.SeccionRepositorio;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
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
    public List<Seccion> findByActiveTrue() {
        return repositorio.findByActiveTrue();
    }

    @Override
    public List<Seccion> findByActiveFalse() {
        return repositorio.findByActiveFalse();
    }

    public List<Seccion> getAllSeccionVigente(){
        return this.repositorio.findAllByActiveIsTrue();
    }

    @Override
    public Seccion get(String clave) throws ResourceNotFoundException {
        return repositorio.findById(clave).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("La sección no está disponible o no existe")
        ));
    }

    public Seccion getSeccionVigente(String clave) throws ResourceNotFoundException {
        return this.repositorio.findFirstByClaveAndActiveIsTrue(clave).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("La sección no es vigente o no existe")
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

    public Seccion softDelete(String clave) throws ResourceNotFoundException {
        Seccion in_bd = this.get(clave);

        in_bd.setActive(false);

        return this.repositorio.save(in_bd);
    }

    public Seccion restore(String clave) throws ResourceNotFoundException {
        Seccion in_bd = this.get(clave);

        in_bd.setActive(true);

        return this.repositorio.save(in_bd);
    }

    public int setSeccionNoActive(String clave) throws ResourceNotFoundException {
        repositorio.setSeccionNoActive(clave);
        Seccion in_bd = get(clave);
        return in_bd.isActive() ? 0 : 1;
    }

    public int setSeccionActive(String clave) throws ResourceNotFoundException {
        repositorio.setSeccionActive(clave);
        Seccion in_bd = get(clave);
        return !in_bd.isActive() ? 0 : 1;
    }

    private Seccion dtoToEntity(SeccionRequestDTO dto){
        return this.mapper.map(dto, Seccion.class);
    }

}
