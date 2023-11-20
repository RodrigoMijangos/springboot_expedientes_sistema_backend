package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.repositories.UnidadAdministrativaRepositorio;
import com.sistema_expedientes.services.interfaces.IUnidadAdministrativaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadAdministrativaServicio implements IUnidadAdministrativaRepositorio {

    @Autowired
    private UnidadAdministrativaRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UnidadAdministrativa get(String id) throws Exception {
        Optional<UnidadAdministrativa> in_db = repositorio.findById(id);

        return in_db.orElseThrow(Exception::new);

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
    public UnidadAdministrativa put(String clave, UnidadAdministrativaRequestDTO request) throws Exception {
        UnidadAdministrativa in_bd = this.get(clave);

        in_bd.setNombre(request.getNombre());
        in_bd.setPiso(request.getPiso());
        in_bd.setExtensionTelefonica(request.getExtensionTelefonica());

        if(request.getUnidadPrincipal() != null){
            try{
                UnidadAdministrativa unidadPrincipal = this.get(request.getUnidadPrincipal());
                in_bd.setUnidadPrincipal(unidadPrincipal);
            }catch (Exception e){
                throw new Exception();
            }
        }

        return this.repositorio.save(in_bd);

    }
}
