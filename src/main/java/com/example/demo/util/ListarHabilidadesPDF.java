package com.example.demo.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Habilidad;
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

@Component("/Vistas/Habilidades/ListaHabilidades")
public class ListarHabilidadesPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Habilidad>listadoHabilidades = (List<Habilidad>) model.get("habilidades");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Habilidades", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(7);
        Font fColumsTitle = FontFactory.getFont("TIMES",16,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 3, 2, 2, 2, 2 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"ID", "Nombre", "Descripción", "Categoria", "Alcance", "Dificultad", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaHabilidades = new PdfPTable(7);
        tablaHabilidades.setWidthPercentage(100);
        Font fTHabilidades = FontFactory.getFont("TIMES",14,Color.black);
        float[] columnWidths = { 1, 2, 3, 2, 2, 2, 2 };
        tablaHabilidades.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);
        
        listadoHabilidades.forEach(habilidad -> {
            PdfPCell cHabilidad = new PdfPCell(new Phrase(habilidad.getId_habilidad().toString(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getNombre_hab(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getDesc_hab(), fTHabilidades));
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getCategoria_hab(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getAlcance_hab(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getDificultad_hab(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
            cHabilidad = new PdfPCell(new Phrase(habilidad.getImagen_hab().toString(), fTHabilidades));
            cHabilidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHabilidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHabilidad.setBorderColor(colorCeldas);
            tablaHabilidades.addCell(cHabilidad);
        });

        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaHabilidades);

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

    //!REPARAR DE MOMENTO NO HAY DATOS POR ESO SALDRA EL NULLEXCEPTION
}
