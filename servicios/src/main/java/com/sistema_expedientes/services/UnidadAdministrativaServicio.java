package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.repositories.UnidadAdministrativaRepositorio;
import com.sistema_expedientes.services.interfaces.IUnidadAdministrativaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadAdministrativaServicio implements IUnidadAdministrativaRepositorio {

    @Autowired
    private UnidadAdministrativaRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

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

        UnidadAdministrativa to_bd = this.mapper.map(request, UnidadAdministrativa.class);

        return this.repositorio.save(to_bd);
    }

    @Override
    public UnidadAdministrativa put(Byte object_id, UnidadAdministrativaRequestDTO request) {
        return null;
    }
}
