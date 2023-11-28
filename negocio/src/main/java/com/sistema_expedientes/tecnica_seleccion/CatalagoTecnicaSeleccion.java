package com.sistema_expedientes.tecnica_seleccion;

import jakarta.persistence.*;

@Entity
@Table(name = "tecnicas_seleccion")
public class CatalagoTecnicaSeleccion {

    @Id
    private Byte identificador;
    private String tecnicaSeleccion;
    private String descripcion;

    public CatalagoTecnicaSeleccion() {
    }

    public CatalagoTecnicaSeleccion(Byte identificador, String tecnicaSeleccion, String descripcion) throws Exception {
        this.identificador = identificador;
        this.tecnicaSeleccion = tecnicaSeleccion;
        this.descripcion = descripcion;
    }

    public Byte getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Byte identificador) {
        this.identificador = identificador;
    }

    public String getTecnicaSeleccion() {
        return tecnicaSeleccion;
    }

    public void setTecnicaSeleccion(String tecnicaSeleccion) {
        this.tecnicaSeleccion = tecnicaSeleccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
