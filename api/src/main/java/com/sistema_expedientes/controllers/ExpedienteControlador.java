package com.sistema_expedientes.controllers;

import com.itextpdf.text.DocumentException;
import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.dto.request.specific.CreateExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.ListaLegajosExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.PUTExpedienteRequestDTO;
import com.sistema_expedientes.expediente.repository.ExpedienteRepositorio;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.expediente.ExpedienteServicio;
import com.sistema_expedientes.services.expediente.reports.PDFService;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Controlador de Expedientes")
public class ExpedienteControlador {

    private final ExpedienteServicio servicio;
    private final LegajoServicio legajoServicio;

    private final PDFService pdfService;
    private final ExpedienteRepositorio expedienteRepositorio;

    public ExpedienteControlador(ExpedienteServicio servicio, LegajoServicio legajoServicio, PDFService pdfService,
                                 ExpedienteRepositorio expedienteRepositorio) {
        this.servicio = servicio;
        this.legajoServicio = legajoServicio;
        this.pdfService = pdfService;
        this.expedienteRepositorio = expedienteRepositorio;
    }

    @Operation(summary = "Listado de todos los expedientes desde db")
    @GetMapping("api/v1/expedientes/list")
    public ResponseEntity<List<Expediente>> list(){
        List<Expediente> response = servicio.list();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Muestra un expediente por id")
    @PostMapping("api/v1/expedientes/get")
    public ResponseEntity<Expediente> get(@RequestBody ExpedienteCompositeKey id) throws Exception {
        return ResponseEntity.ok(servicio.get(id));
    }

    @Operation(summary = "Creacion de un nuevo expediente")
    @PostMapping("api/v1/expedientes/create")
    public ResponseEntity<Expediente> create(@Valid @RequestBody CreateExpedienteRequestDTO request) throws Exception {
        Expediente to_bd = servicio.create(request);

        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(201).body(to_bd);

    }

    @Operation(summary = "Creacion de un legajo dentro del expediente por id")
    @PostMapping("api/v1/expedientes/crear_legajo")
    public ResponseEntity<Legajo> create(@RequestBody CreateLegajoRequestDTO request) throws Exception {
        return ResponseEntity.status(201).body(this.legajoServicio.create(request, this.servicio.get(request.getExpediente())));
    }

    @Operation(summary = "Creacion de una lista de legajos dentro del expediente por id")
    @PostMapping("api/v1/expedientes/crear_lista_legajos")
    public ResponseEntity<Expediente> createList(@RequestBody ListaLegajosExpedienteRequestDTO request) throws Exception {
        return ResponseEntity.ok(this.servicio.guardarListaLegajos(request));
    }

    @Operation(summary = "Edicion de datos de un expediente por id")
    @PutMapping("api/v1/expedientes/edit")
    public ResponseEntity<Expediente> put(@RequestBody PUTExpedienteRequestDTO request) throws Exception {
        Expediente to_bd = servicio.put(request);
        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(to_bd);
    }

    @Operation(summary = "Eliminacion definitiva de un expediente por id")
    @DeleteMapping("api/v1/expedientes/delete")
    public ResponseEntity<String> delete(@RequestBody ExpedienteCompositeKey id) throws Exception {
        this.servicio.delete(id);
        return ResponseEntity.ok("Ha sido eliminado");
    }

    @Operation(summary = "Genera la caratula del expediente por id")
    @PostMapping("api/v1/expedientes/caratula")
    public ResponseEntity<byte[]> generarCaratulaPDF(@RequestBody ExpedienteCompositeKey id) throws ResourceNotFoundException {
        Expediente expediente = servicio.get(id);
        if (expediente != null) {
            try {
                byte[] pdfBytes = pdfService.generarCaratulaPDF(expediente);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "caratula_" + System.currentTimeMillis() + ".pdf");
                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } catch (DocumentException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
