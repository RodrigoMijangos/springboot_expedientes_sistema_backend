package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;

import java.util.List;

public interface IUnidadAdministrativaRepositorio {

    public UnidadAdministrativa get(Byte id);

    public List<UnidadAdministrativa> list();

    public UnidadAdministrativa create(UnidadAdministrativaRequestDTO request);

    public UnidadAdministrativa put(Byte object_id, UnidadAdministrativaRequestDTO request);

}
