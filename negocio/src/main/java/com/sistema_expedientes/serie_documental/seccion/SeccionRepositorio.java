package com.sistema_expedientes.serie_documental.seccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeccionRepositorio extends JpaRepository<Seccion, String> {

    List<Seccion> findAllByVigenteIsTrue();

    Optional<Seccion> findFirstByIdAndVigenteIsTrue(String clave);

    @Query("UPDATE Seccion s SET s.vigente = false WHERE s.clave = :clave AND s.vigente = true")
    void setSeccionNoVigente(String clave);

    @Query("UPDATE Seccion s SET s.vigente = true WHERE s.clave = :clave AND s.vigente = false")
    void setSeccionVigente(String clave);
}
