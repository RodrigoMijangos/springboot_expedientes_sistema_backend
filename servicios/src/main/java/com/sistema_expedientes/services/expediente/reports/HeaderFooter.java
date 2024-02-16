package com.sistema_expedientes.services.expediente.reports;

import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooter extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            // Ruta de las imágenes
            String imagePathLeft = "api/src/main/resources/gobierno-logo.png";
            String imagePathCenter = "api/src/main/resources/conacyt.png";
            String imagePathRight = "api/src/main/resources/infotec.png";

            // Agregar imágenes a cada celda
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorderWidth(0);
            Image imagenIzquierda = Image.getInstance(imagePathLeft);
            imagenIzquierda.scaleAbsolute(150, 50);
            cell1.addElement(imagenIzquierda);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBorderWidth(0);
            Image imagenCentro = Image.getInstance(imagePathCenter);
            imagenIzquierda.scaleAbsolute(150, 50);
            imagenCentro.setAlignment(Image.ALIGN_CENTER);
            cell2.addElement(imagenCentro);

            PdfPCell cell3 = new PdfPCell();
            cell3.setBorderWidth(0);
            Image imagenDerecha = Image.getInstance(imagePathRight);
            imagenIzquierda.scaleAbsolute(150, 50);
            cell3.addElement(imagenDerecha);

            // Agregar celdas a la tabla
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            // Verificar si la tabla tiene contenido antes de agregarla al documento
            if (table.getTotalWidth() > 0) {
                table.writeSelectedRows(0, -1, 0, document.top() + 10, writer.getDirectContent());
            } else {
                System.out.println("La tabla no tiene contenido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
