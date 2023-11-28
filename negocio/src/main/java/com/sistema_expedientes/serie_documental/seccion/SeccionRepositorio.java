package com.sistema_expedientes.serie_documental.seccion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionRepositorio extends JpaRepository<Seccion, String> {

    /*@Modifying
    @Query("update Seccion s ")
    public Seccion updateSeccionByClave(String clave, Seccion request);*/
}
