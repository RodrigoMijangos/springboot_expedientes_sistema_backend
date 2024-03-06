package com.sistema_expedientes.controllers;

import com.sistema_expedientes.services.dashboard.DashboardServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    public final DashboardServicio dashboardServicio;

    public DashboardController(DashboardServicio dashboardServicio) {
        this.dashboardServicio = dashboardServicio;
    }

    @GetMapping("/totalExpedientes")
    public int obtenerTotalExpedientes() {
        return dashboardServicio.getTotalExpedientes();
    }

    @GetMapping("/totalUnidadesAdministrativas")
    public int obtenerTotalUnidadesAdministrativas() {
        return dashboardServicio.getTotalUnidadesAdministrativas();
    }

    @GetMapping("/totalDocumentos")
    public int obtenerTotalDocumentos() {
        return dashboardServicio.getTotalDocumentos();
    }

}
