package com.sistema_expedientes.controllers;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.dto.request.specific.CreateExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.ListaLegajosExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.PUTExpedienteRequestDTO;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.services.expediente.ExpedienteServicio;
import com.sistema_expedientes.services.expediente.reports.ExpedienteReportGenerator;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ExpedienteControlador {

    private final ExpedienteServicio servicio;
    private final LegajoServicio legajoServicio;
    private final ExpedienteReportGenerator expedienteReport;

    public ExpedienteControlador(ExpedienteServicio servicio, LegajoServicio legajoServicio, ExpedienteReportGenerator expedienteReport) {
        this.servicio = servicio;
        this.legajoServicio = legajoServicio;
        this.expedienteReport = expedienteReport;
    }

    @GetMapping("api/v1/expedientes/list")
    public ResponseEntity<List<Expediente>> list(){
        List<Expediente> response = servicio.list();

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);

    }

    @PostMapping("api/v1/expedientes/get")
    public ResponseEntity<Expediente> get(@RequestBody ExpedienteCompositeKey id) throws Exception {
        return ResponseEntity.ok(servicio.get(id));
    }

    @PostMapping("api/v1/expedientes/create")
    public ResponseEntity<Expediente> create(@RequestBody CreateExpedienteRequestDTO request) throws Exception {

        Expediente to_bd = servicio.create(request);

        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(201).body(to_bd);

    }

    @PostMapping("api/v1/expedientes/crear_legajo")
    public ResponseEntity<Legajo> create(@RequestBody CreateLegajoRequestDTO request) throws Exception {
        return ResponseEntity.status(201).body(this.legajoServicio.create(request, this.servicio.get(request.getExpediente())));
    }

    @PostMapping("api/v1/expedientes/crear_lista_legajos")
    public ResponseEntity<Expediente> createList(@RequestBody ListaLegajosExpedienteRequestDTO request) throws Exception {
        return ResponseEntity.ok(this.servicio.guardarListaLegajos(request));
    }

    @PutMapping("api/v1/expedientes/edit")
    public ResponseEntity<Expediente> put(@RequestBody PUTExpedienteRequestDTO request) throws Exception {
        Expediente to_bd = servicio.put(request);
        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(to_bd);
    }

    @DeleteMapping("api/v1/expedientes/delete")
    public ResponseEntity<String> delete(@RequestBody ExpedienteCompositeKey id) throws Exception {
        this.servicio.delete(id);
        return ResponseEntity.ok("Ha sido eliminado");
    }

    @GetMapping("api/v1/expediente/download-report")
    public ResponseEntity<byte[]> exportToPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("expedienteReport", "expediente-report.pdf");
        return ResponseEntity.ok().headers(headers).body(exportToPdf().getBody());
    }
}
