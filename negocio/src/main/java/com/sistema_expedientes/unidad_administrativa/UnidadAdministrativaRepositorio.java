package com.sistema_expedientes.unidad_administrativa;

import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadAdministrativaRepositorio extends JpaRepository<UnidadAdministrativa, String> {

    List<UnidadAdministrativa> findByActiveTrue(); // Consulta para recuperar entidades activas
    List<UnidadAdministrativa> findByActiveFalse();
}
