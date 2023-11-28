package com.sistema_expedientes.unidad_administrativa;

import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdministrativaRepositorio extends JpaRepository<UnidadAdministrativa, String> { }
