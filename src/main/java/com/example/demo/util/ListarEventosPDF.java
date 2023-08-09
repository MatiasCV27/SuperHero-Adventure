package com.example.demo.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Evento;
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

@Component("/Vistas/Eventos/ListaEventos")
public class ListarEventosPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Evento>listadoEventos = (List<Evento>) model.get("eventos");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Eventos", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setBorderWidth(0);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(6);
        Font fColumsTitle = FontFactory.getFont("TIMES",16,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 3, 2, 2, 2 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"ID", "Nombre", "Descripción", "Lugar", "Fecha del suceso", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaEventos = new PdfPTable(6);
        tablaEventos.setWidthPercentage(100);
        Font fTEventos = FontFactory.getFont("TIMES",14,Color.black);
        float[] columnWidths = { 1, 2, 3, 2, 2, 2 };
        tablaEventos.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);
        SimpleDateFormat fEvento = new SimpleDateFormat("yyyy-MM-dd");

        listadoEventos.forEach(evento -> {
            PdfPCell cEvento = new PdfPCell(new Phrase(evento.getId_evento().toString(), fTEventos));
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
            cEvento = new PdfPCell(new Phrase(evento.getNombre_eve(), fTEventos));
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
            cEvento = new PdfPCell(new Phrase(evento.getDesc_eve(), fTEventos));
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
            cEvento = new PdfPCell(new Phrase(evento.getLugar_eve(), fTEventos));
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
            cEvento = new PdfPCell(new Phrase(fEvento.format(evento.getFecha_eve()).toString(), fTEventos));
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
            cEvento = new PdfPCell(new Phrase(evento.getImagen_eve().toString(), fTEventos));
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            tablaEventos.addCell(cEvento);
        });
        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaEventos);

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