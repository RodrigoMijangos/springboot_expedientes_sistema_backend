package com.sistema_expedientes.services.expediente.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema_expedientes.expediente.Expediente;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFService {

    public byte[] generarCaratulaPDF(Expediente expediente) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter pdfWriter =  PdfWriter.getInstance(document, outputStream);
        document.open();

        /*HeaderFooter event = new HeaderFooter();
        pdfWriter.setPageEvent(event);*/

        // Agregar imagen de encabezado
        Image imagenEncabezado = Image.getInstance("api/src/main/resources/infotec.png");
        imagenEncabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(imagenEncabezado);

        // Agregar espacio en blanco
        // document.add(new Paragraph("\n\n\n"));

        // Agregar nombre del proyecto (en negrita y más grande)
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36);
        Paragraph nombreProyecto = new Paragraph(expediente.getNombreProyecto(), fontTitulo);
        Rectangle pageSize = document.getPageSize();
        float titleHeight = nombreProyecto.getFont().getSize();
        float verticalPosition = (pageSize.getHeight() - titleHeight) / 4;
        nombreProyecto.setSpacingBefore(verticalPosition);
        nombreProyecto.setAlignment(Element.ALIGN_CENTER);
        document.add(nombreProyecto);

        // Agregar unidad administrativa generadora (en negrita y más grande)
        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph unidadAdministrativa = new Paragraph(expediente.getUnidadAdministrativaGeneradora(), fontSubtitulo);
        unidadAdministrativa.setAlignment(Element.ALIGN_CENTER);
        document.add(unidadAdministrativa);

        // Agregar espacio en blanco
        document.add(new Paragraph("\n"));

        document.close();

        return outputStream.toByteArray();
    }
}
