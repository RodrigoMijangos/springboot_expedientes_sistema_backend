package com.sistema_expedientes.services.tipos.tipo_expediente;

import com.sistema_expedientes.auth.repository.RoleRepository;
import com.sistema_expedientes.tipos.tipo_expediente.TipoExpediente;
import com.sistema_expedientes.tipos.tipo_expediente.TipoExpedienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoExpedienteServicio implements TipoExpedienteMetodos{

    private final TipoExpedienteRepositorio repositorio;
    private final RoleRepository roleRepository;

    public TipoExpedienteServicio(TipoExpedienteRepositorio repositorio,
                                  RoleRepository roleRepository) {
        this.repositorio = repositorio;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<TipoExpediente> findAllByVigenteIsTrue() {
        return repositorio.findAllByVigenteIsTrue();
    }

    @Override
    public Optional<TipoExpediente> findByIdentificadorAndVigenteIsTrue(Short identificador) {
        return repositorio.findByIdentificadorAndVigenteIsTrue(identificador);
    }

    @Override
    public void setTipoExpedienteNoVigente(Short identificador) {
        repositorio.setTipoExpedienteNoVigente(identificador);
    }

    @Override
    public void setTipoExpedienteVigente(Short identificador) {
        repositorio.setTipoExpedienteVigente(identificador);
    }

    @Override
    public Optional<Boolean> findByVigenteTrue(short identificador) {
        return repositorio.findByVigenteTrue(identificador);
    }

    /*@Override
    public Optional<Boolean> isTipoExpedienteVigente(Short identificador) {
        return repositorio.isTipoExpedienteVigente(identificador);
    }*/
}
