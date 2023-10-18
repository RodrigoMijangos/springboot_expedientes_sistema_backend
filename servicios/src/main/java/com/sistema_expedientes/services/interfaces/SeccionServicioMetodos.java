package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Seccion;
import com.sistema_expedientes.entities.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;

import java.util.List;

public interface SeccionServicioMetodos {

    public List<Seccion> getAll();
    public Seccion get(String clave) throws SeccionNoEncontradaException;
    public Seccion create(SeccionRequestDTO request);
    public Seccion put(String clave, SeccionRequestDTO request) throws SeccionNoEncontradaException;
    public int delete(String clave) throws SeccionNoEncontradaException;

}
