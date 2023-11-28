package com.sistema_expedientes.services.formatter;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.services.serie_documental.SerieDocumentalServicio;
import org.springframework.stereotype.Service;

@Service
public class FolderInstanceNameFormatter {

    private final SerieDocumentalServicio serieDocumentalServicio;

    public FolderInstanceNameFormatter(SerieDocumentalServicio serieDocumentalServicio){

        this.serieDocumentalServicio = serieDocumentalServicio;
    }

    public String setGoogleDriveFolderName(Expediente expediente) throws Exception {
        SerieDocumental serieDocumental = this.serieDocumentalServicio.get(expediente.getIdentificadorSerieDocumental());

        StringBuilder folderNameBuffer = new StringBuilder();

        if(serieDocumental.getSeccion() != null){
            folderNameBuffer
                    .append(serieDocumental.getSeccion().getClave())
                    .append(".")
                    .append(serieDocumental.getClave());
        }else{
            assert serieDocumental.getSeriePadre() != null;
            assert serieDocumental.getSeriePadre().getSeccion() != null;
            folderNameBuffer
                    .append(serieDocumental.getSeriePadre().getSeccion().getClave())
                    .append(".")
                    .append(serieDocumental.getSeriePadre().getClave())
                    .append(".")
                    .append(serieDocumental.getClave());
        }

        return folderNameBuffer
                .append("-")
                .append(String.format("%05d", expediente.getNumeroExpediente()))
                .append("_")
                .append(expediente.getFechaApertura())
                .toString();

    }

    public String setGoogleDriveFolderName(Legajo legajo, Expediente expediente) throws Exception {

        return this.setGoogleDriveFolderName(expediente) +
                "-" + legajo.getId().getNumeroLegajo();

    }

}
