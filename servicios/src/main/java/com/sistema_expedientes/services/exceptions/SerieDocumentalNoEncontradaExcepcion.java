package com.sistema_expedientes.services.exceptions;

public class SerieDocumentalNoEncontradaExcepcion extends Exception{

    public SerieDocumentalNoEncontradaExcepcion(long identificador) {
        super("No se encontró la serie documental con identificador : " + identificador);
    }
}
