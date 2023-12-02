package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.seccion.Seccion;
import com.sistema_expedientes.serie_documental.dto.request.SeccionRequestDTO;

import java.util.List;

public interface SeccionServicioMetodos {

    List<Seccion> getAll();
    Seccion get(String clave) throws Exception;
    Seccion create(SeccionRequestDTO request);
    Seccion put(String clave, SeccionRequestDTO request) throws Exception;
    int delete(String clave) throws Exception;

}
