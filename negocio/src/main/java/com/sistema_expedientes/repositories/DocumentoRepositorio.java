package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DocumentoRepositorio extends JpaRepository<Documento, BigInteger> {}
