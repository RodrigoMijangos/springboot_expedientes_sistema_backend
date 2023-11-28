package com.sistema_expedientes.services.unidad_administrativa;

import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import com.sistema_expedientes.unidad_administrativa.dto.request.UnidadAdministrativaRequestDTO;

import java.util.List;

public interface UnidadAdministrativaMetodos {

    UnidadAdministrativa get(String id) throws Exception;

    List<UnidadAdministrativa> list();

    UnidadAdministrativa create(UnidadAdministrativaRequestDTO request) throws Exception;

    UnidadAdministrativa put(String clave, UnidadAdministrativaRequestDTO request) throws Exception;
}
