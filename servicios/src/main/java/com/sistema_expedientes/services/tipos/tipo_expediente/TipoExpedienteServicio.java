package com.sistema_expedientes.services.tipos.tipo_expediente;

import com.sistema_expedientes.tipos.tipo_expediente.TipoExpedienteRepositorio;
import org.springframework.stereotype.Service;

@Service
public class TipoExpedienteServicio {

    private final TipoExpedienteRepositorio repositorio;

    public TipoExpedienteServicio(TipoExpedienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

}
