package com.sistema_expedientes.legajo.dto.request.base;

import com.sistema_expedientes.base.request._DTO;
import jakarta.validation.constraints.NotBlank;

public class LegajoRequestDTO extends _DTO {

    @NotBlank
    private String numeroMueble;
    @NotBlank
    private String letraEstante;
    @NotBlank
    private String numeroPasillo;
    @NotBlank
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
