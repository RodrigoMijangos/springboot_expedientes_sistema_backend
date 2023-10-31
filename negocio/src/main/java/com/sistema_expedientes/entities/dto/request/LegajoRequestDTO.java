package com.sistema_expedientes.entities.dto.request;

import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;

import java.time.LocalDate;

public class LegajoRequestDTO extends _DTO{

    private ExpedienteCompositeKey expediente;
    private String numeroMueble;
    private String letraEstante;
    private String numeroPasillo;
    private String letraBateria;

    public LegajoRequestDTO() {
    }

    public LegajoRequestDTO(ExpedienteCompositeKey expediente, String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria) {
        this.expediente = expediente;
        this.numeroMueble = numeroMueble;
        this.letraEstante = letraEstante;
        this.numeroPasillo = numeroPasillo;
        this.letraBateria = letraBateria;
    }

    public ExpedienteCompositeKey getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteCompositeKey expediente) {
        this.expediente = expediente;
    }

    public String getNumeroMueble() {
        return numeroMueble;
    }

    public void setNumeroMueble(String numeroMueble) {
        this.numeroMueble = numeroMueble;
    }

    public String getLetraEstante() {
        return letraEstante;
    }

    public void setLetraEstante(String letraEstante) {
        this.letraEstante = letraEstante;
    }

    public String getNumeroPasillo() {
        return numeroPasillo;
    }

    public void setNumeroPasillo(String numeroPasillo) {
        this.numeroPasillo = numeroPasillo;
    }

    public String getLetraBateria() {
        return letraBateria;
    }

    public void setLetraBateria(String letraBateria) {
        this.letraBateria = letraBateria;
    }
}
