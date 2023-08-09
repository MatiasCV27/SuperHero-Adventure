package com.example.demo.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Evento;
import com.example.demo.entity.models.Habilidad;
import com.example.demo.entity.models.SuperHeroe;
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

@Component("/Vistas/SuperHeroes/ListaSuperH")
public class ListarSuperHeroePDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<SuperHeroe>listadoSuperH = (List<SuperHeroe>) model.get("superheroe");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Super-Heroes", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(15);
        Font fColumsTitle = FontFactory.getFont("TIMES",10,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 2, 2, 1, 1, 2, 2, 3, 2, 2, 2, 2, 2, 3 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"#", "Nombre", "Alias", "Nacimi-ento", "E", "G", "Nacion-alidad", "Debilidad", "Descripción", "Organi-zación",  "Planeta", "Villanos", "Habili-dades", "Eventos", "Imagen"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaSuperH = new PdfPTable(15);
        tablaSuperH.setWidthPercentage(100);
        Font fTSuperH = FontFactory.getFont("TIMES",10,Color.black);
        float[] columnWidths = { 1, 2, 2, 2, 1, 1, 2, 2, 3, 2, 2, 2, 2, 2, 3 };
        tablaSuperH.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);
        SimpleDateFormat fSuperH = new SimpleDateFormat("yyyy-MM-dd");

        listadoSuperH.forEach(superH -> {

            PdfPCell cSuperH = new PdfPCell(new Phrase(superH.getId_superheroe().toString(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getNombre_sh(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getAlias_sh(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(fSuperH.format(superH.getFecha_naci_sh()).toString(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getEdad_sh().toString(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getGenero_sh(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getNacionalidad_sh(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getDebilidad_sh(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getDesc_sh(), fTSuperH));
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getOrganizacion().getNombre_org(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);
            cSuperH = new PdfPCell(new Phrase(superH.getPlaneta().getNombre_pla(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);

            PdfPCell cVillanos = new PdfPCell();
            cVillanos.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVillanos.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cVillanos.setBorderColor(colorCeldas);
            StringBuilder nVillanos = new StringBuilder();
            for (Villano villano : superH.getVillano()) nVillanos.append(villano.getNombre_vil()).append(", ");
            if (nVillanos.length() > 2) nVillanos.setLength(nVillanos.length() - 2);
            cVillanos.addElement(new Phrase(nVillanos.toString(), fTSuperH));
            tablaSuperH.addCell(cVillanos);

            PdfPCell cHab = new PdfPCell();
            cHab.setHorizontalAlignment(Element.ALIGN_CENTER);
            cHab.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cHab.setBorderColor(colorCeldas);
            StringBuilder nHab = new StringBuilder();
            for (Habilidad habilidad : superH.getHabilidad()) nHab.append(habilidad.getNombre_hab()).append(", ");
            if (nHab.length() > 2) nHab.setLength(nHab.length() - 2);
            cHab.addElement(new Phrase(nHab.toString(), fTSuperH));
            tablaSuperH.addCell(cHab);

            PdfPCell cEvento = new PdfPCell();
            cEvento.setHorizontalAlignment(Element.ALIGN_CENTER);
            cEvento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cEvento.setBorderColor(colorCeldas);
            StringBuilder nEvento = new StringBuilder();
            for (Evento evento : superH.getEvento()) nEvento.append(evento.getNombre_eve()).append(", ");
            if (nEvento.length() > 2) nEvento.setLength(nEvento.length() - 2);
            cEvento.addElement(new Phrase(nEvento.toString(), fTSuperH));
            tablaSuperH.addCell(cEvento);

            cSuperH = new PdfPCell(new Phrase(superH.getImagen_sh().toString(), fTSuperH));
            cSuperH.setHorizontalAlignment(Element.ALIGN_CENTER);
            cSuperH.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cSuperH.setBorderColor(colorCeldas);
            tablaSuperH.addCell(cSuperH);

        });

        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaSuperH);

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