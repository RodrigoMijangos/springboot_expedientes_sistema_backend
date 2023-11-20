package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;

import java.util.List;

public interface IUnidadAdministrativaRepositorio {

    UnidadAdministrativa get(String id) throws Exception;

    List<UnidadAdministrativa> list();

    UnidadAdministrativa create(UnidadAdministrativaRequestDTO request);

    UnidadAdministrativa put(String clave, UnidadAdministrativaRequestDTO request) throws Exception;
}
