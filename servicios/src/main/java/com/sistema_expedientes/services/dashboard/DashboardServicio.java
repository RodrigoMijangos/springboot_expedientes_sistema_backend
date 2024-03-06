package com.sistema_expedientes.services.dashboard;

import com.sistema_expedientes.documento.repository.DocumentoRepositorio;
import com.sistema_expedientes.expediente.repository.ExpedienteRepositorio;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class DashboardServicio implements DashboardServicioMetodos{

    public final ExpedienteRepositorio expedienteRepositorio;
    public final UnidadAdministrativaRepositorio unidadAdministrativaRepositorio;

    public final DocumentoRepositorio documentoRepositorio;

    public DashboardServicio(ExpedienteRepositorio expedienteRepositorio, UnidadAdministrativaRepositorio unidadAdministrativaRepositorio, DocumentoRepositorio documentoRepositorio) {
        this.expedienteRepositorio = expedienteRepositorio;
        this.unidadAdministrativaRepositorio = unidadAdministrativaRepositorio;
        this.documentoRepositorio = documentoRepositorio;
    }

    @Override
    public int getTotalExpedientes() {
        return (int) expedienteRepositorio.count();
    }

    @Override
    public int getTotalUnidadesAdministrativas() {
        return (int) unidadAdministrativaRepositorio.count();
    }

    @Override
    public int getTotalDocumentos() {
        return (int) documentoRepositorio.count();
    }
}
