package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.repositories.UnidadAdministrativaRepositorio;
import com.sistema_expedientes.services.interfaces.IUnidadAdministrativaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadAdministrativaServicio implements IUnidadAdministrativaRepositorio {

    @Autowired
    private UnidadAdministrativaRepositorio repositorio;

    @Override
    public UnidadAdministrativa get(Byte id) {
        Optional<UnidadAdministrativa> in_db = repositorio.findById(id);

        return in_db.orElse(null);

    }

    @Override
    public List<UnidadAdministrativa> list() {
        return repositorio.findAll();
    }

    @Override
    public UnidadAdministrativa create(UnidadAdministrativaRequestDTO request) {

        return repositorio.save(new UnidadAdministrativa(request.getId(), request.getClave(), request.getNombre()));
    }

    @Override
    public UnidadAdministrativa put(Byte object_id, UnidadAdministrativaRequestDTO request) {
        Optional<UnidadAdministrativa> in_db = repositorio.findById(object_id);

        if(in_db.isPresent()){
            UnidadAdministrativa to_bd = in_db.get();
            Optional<UnidadAdministrativa> check = repositorio.findById(request.getId());
            if(check.isEmpty()){
                to_bd.setId(request.getId());
                to_bd.setClave(request.getClave());
                to_bd.setNombre(request.getNombre());
                return repositorio.save(to_bd);
            }
            return null;
        }

        return null;
    }
}
