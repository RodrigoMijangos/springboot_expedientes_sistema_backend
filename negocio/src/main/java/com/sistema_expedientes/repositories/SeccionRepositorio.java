package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SeccionRepositorio extends JpaRepository<Seccion, String> {

    /*@Modifying
    @Query("update Seccion s ")
    public Seccion updateSeccionByClave(String clave, Seccion request);*/
}
