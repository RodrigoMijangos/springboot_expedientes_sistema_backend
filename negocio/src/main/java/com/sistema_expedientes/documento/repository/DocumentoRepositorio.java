package com.sistema_expedientes.documento.repository;

import com.sistema_expedientes.documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepositorio extends JpaRepository<Documento, Long>{}
