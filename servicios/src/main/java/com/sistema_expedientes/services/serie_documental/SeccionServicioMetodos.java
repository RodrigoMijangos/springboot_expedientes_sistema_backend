package com.sistema_expedientes.services.serie_documental;

import com.sistema_expedientes.serie_documental.seccion.Seccion;
import com.sistema_expedientes.serie_documental.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;

import java.util.List;

public interface SeccionServicioMetodos {

    public List<Seccion> getAll();
    public Seccion get(String clave) throws SeccionNoEncontradaException;
    public Seccion create(SeccionRequestDTO request);
    public Seccion put(String clave, SeccionRequestDTO request) throws SeccionNoEncontradaException;
    public int delete(String clave) throws SeccionNoEncontradaException;

}
