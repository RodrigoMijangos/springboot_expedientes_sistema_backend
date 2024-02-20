package com.sistema_expedientes.serie_documental.seccion;

import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeccionRepositorio extends JpaRepository<Seccion, String> {

    List<Seccion> findAllByActiveIsTrue();

    Optional<Seccion> findFirstByClaveAndActiveIsTrue(String clave);

    @Query("UPDATE Seccion s SET s.active = false WHERE s.clave = :clave AND s.active = true")
    void setSeccionNoActive(String clave);

    @Query("UPDATE Seccion s SET s.active = true WHERE s.clave = :clave AND s.active = false")
    void setSeccionActive(String clave);

    List<Seccion> findByActiveTrue();
    List<Seccion> findByActiveFalse();
}
