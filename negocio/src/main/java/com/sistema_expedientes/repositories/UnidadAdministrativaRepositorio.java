package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdministrativaRepositorio extends JpaRepository<UnidadAdministrativa, String> { }
