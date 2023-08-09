package com.example.demo.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Organizacion;
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

@Component("/Vistas/Organizaciones/ListaOrganizaciones")
public class ListarOrganizacionesPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Organizacion>listadoOrganizaciones = (List<Organizacion>) model.get("organizaciones");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de las Organizaciones", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(9);
        Font fColumsTitle = FontFactory.getFont("TIMES",13,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 2, 2, 2, 3, 2, 2, 2 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"ID", "Nombre", "Tipo", "Base", "Objetivos", "Descripción", "Fundación", "Financiami-ento", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaOrgs = new PdfPTable(9);
        tablaOrgs.setWidthPercentage(100);
        Font fTOrgs = FontFactory.getFont("TIMES",13,Color.black);
        float[] columnWidths = { 1, 2, 2, 2, 2, 3, 2, 2, 2 };
        tablaOrgs.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);
        SimpleDateFormat fechOrgs = new SimpleDateFormat("yyyy-MM-dd");

        listadoOrganizaciones.forEach(orgs -> {
            PdfPCell cOrgs = new PdfPCell(new Phrase(orgs.getId_organizacion().toString(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getNombre_org(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getTipo_org(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getBase_opera_org(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getObjetivos_org(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getDesc_org(), fTOrgs));
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(fechOrgs.format(orgs.getFecha_creacion_org()).toString(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getFinanciamiento_org(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
            cOrgs = new PdfPCell(new Phrase(orgs.getImagen_org().toString(), fTOrgs));
            cOrgs.setHorizontalAlignment(Element.ALIGN_CENTER);
            cOrgs.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cOrgs.setBorderColor(colorCeldas);
            tablaOrgs.addCell(cOrgs);
        });

        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaOrgs);

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
