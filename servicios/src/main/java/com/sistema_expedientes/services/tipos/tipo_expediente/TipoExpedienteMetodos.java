package com.sistema_expedientes.services.tipos.tipo_expediente;

import com.sistema_expedientes.tipos.tipo_expediente.TipoExpediente;

import java.util.List;
import java.util.Optional;

public interface TipoExpedienteMetodos {

    List<TipoExpediente> findAllByVigenteIsTrue();

    Optional<TipoExpediente> findByIdentificadorAndVigenteIsTrue(Short identificador);

    void setTipoExpedienteNoVigente(Short identificador);

    void setTipoExpedienteVigente(Short identificador);

     //Optional<Boolean> isTipoExpedienteVigente(Short identificador);

    Optional<Boolean> findByVigenteTrue(short identificador);
}
