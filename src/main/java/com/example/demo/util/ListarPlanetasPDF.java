package com.example.demo.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Planeta;
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

@Component("/Vistas/Planetas/ListaPlanetas")
public class ListarPlanetasPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Planeta>listadoPlanetas = (List<Planeta>) model.get("planetas");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Planetas", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(8);
        Font fColumsTitle = FontFactory.getFont("TIMES",13,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 2, 3, 2, 2, 1, 2 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"ID", "Nombre", "Tipo", "Descripción", "Poder", "Tecnología", "Lunas", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaPlanetas = new PdfPTable(8);
        tablaPlanetas.setWidthPercentage(100);
        Font fTPlanetas = FontFactory.getFont("TIMES",13,Color.black);
        float[] columnWidths = { 1, 2, 2, 3, 2, 2, 1, 2 };
        tablaPlanetas.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);

        listadoPlanetas.forEach(planeta -> {
            PdfPCell cPlaneta = new PdfPCell(new Phrase(planeta.getId_planeta().toString(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getNombre_pla(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getTipo_pla(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getDesc_pla(), fTPlanetas));
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getPoder_pla(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getTecnologia_pla(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getNro_lunas_pla().toString(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
            cPlaneta = new PdfPCell(new Phrase(planeta.getImagen_pla().toString(), fTPlanetas));
            cPlaneta.setHorizontalAlignment(Element.ALIGN_CENTER);
            cPlaneta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPlaneta.setBorderColor(colorCeldas);
            tablaPlanetas.addCell(cPlaneta);
        });
        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaPlanetas);

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
