package com.sistema_expedientes.entities.dto.request;

import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;

import java.time.LocalDate;

public class LegajoRequestDTO extends _DTO{

    private String numeroMueble;
    private String letraEstante;
    private String numeroPasillo;
    private String letraBateria;

    public LegajoRequestDTO() {
    }

    public LegajoRequestDTO(String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria) {
        this.numeroMueble = numeroMueble;
        this.letraEstante = letraEstante;
        this.numeroPasillo = numeroPasillo;
        this.letraBateria = letraBateria;
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
