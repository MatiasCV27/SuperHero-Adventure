package com.example.demo.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Villano;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("/Vistas/Villanos/ListaVillanos")
public class ListarVillanosPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Villano>listadoVillanos = (List<Villano>) model.get("villanos");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Villanos", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(9);
        Font fColumsTitle = FontFactory.getFont("TIMES",12,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 3, 2, 2, 1, 2, 2, 2 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"ID", "Nombre", "Descripción", "Personalidad", "Nacimiento", "Edad", "Amenaza", "Inteligencia", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaVillanos = new PdfPTable(9);
        tablaVillanos.setWidthPercentage(100);
        Font fTVillanos = FontFactory.getFont("TIMES",12,Color.black);
        float[] columnWidths = { 1, 2, 3, 2, 2, 1, 2, 2, 2 };
        tablaVillanos.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);
        SimpleDateFormat fVillano = new SimpleDateFormat("yyyy-MM-dd");

        listadoVillanos.forEach(villano -> {
            PdfPCell cVillano = new PdfPCell(new Phrase(villano.getId_villano().toString(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getNombre_vil(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getDesc_vil(), fTVillanos));
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getPersonalidad_vil(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(fVillano.format(villano.getFecha_naci_vil()).toString(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getEdad_vil().toString(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getNivel_amenaza_vil(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getInteligencia_vil(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
            cVillano = new PdfPCell(new Phrase(villano.getImagen_vil().toString(), fTVillanos));
            cVillano.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillano.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillano.setBorderColor(colorCeldas);
            tablaVillanos.addCell(cVillano);
        });

        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaVillanos);

        PdfContentByte content = writer.getDirectContent();
        float x1 = document.leftMargin();
        float y1 = document.bottomMargin();
        float x2 = document.getPageSize().getWidth() - document.rightMargin();
        float y2 = document.getPageSize().getHeight() - document.topMargin();
        float red = 88f / 255f; 
        float green = 21f / 255f;
        float blue = 28f / 255f;
        content.setColorStroke(new Color(red, green, blue));
        content.setLineWidth(2f);
        content.rectangle(x1, y1, x2 - x1, y2 - y1);
        content.stroke();
    }
}