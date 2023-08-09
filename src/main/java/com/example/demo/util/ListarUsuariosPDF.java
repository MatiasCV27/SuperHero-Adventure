package com.example.demo.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.models.Roles;
import com.example.demo.entity.models.Usuarios;
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

@Component("/Vistas/Usuarios/ListaUsuarios")
public class ListarUsuariosPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Usuarios>listadoUsuarios = (List<Usuarios>) model.get("users");

        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(30, 30, 30, 30);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        PdfPCell celda = null;
        Font fTitulo = FontFactory.getFont("TIMES",22,Color.white);
        celda = new PdfPCell(new Phrase("Lista de los Usuarios", fTitulo));
        celda.setBackgroundColor(new Color(58, 0, 7));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(0);

        PdfPTable tablaTColumns = new PdfPTable(5);
        Font fColumsTitle = FontFactory.getFont("TIMES",14,Color.white);
        tablaTColumns.setWidthPercentage(100);
        tablaTColumns.setSpacingAfter(0);
        float[] columnTWidths = { 1, 2, 3, 1, 3 };
        tablaTColumns.setWidths(columnTWidths);
        String[] textColumns = {"#", "Nombre de Usuario", "Contraseña", "Actividad", "Roles"};
        for (String text : textColumns) {
            PdfPCell celdaTitulo = new PdfPCell(new Phrase(text, fColumsTitle));
            celdaTitulo.setBackgroundColor(new Color(88,21,28));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setBorderWidth(0); 
            celdaTitulo.setPadding(10);
            tablaTColumns.addCell(celdaTitulo);
        }

        PdfPTable tablaUsuarios = new PdfPTable(5);
        tablaUsuarios.setWidthPercentage(100);
        Font fTUsuarios = FontFactory.getFont("TIMES",13,Color.black);
        float[] columnWidths = { 1, 2, 3, 1, 3 };
        tablaUsuarios.setWidths(columnWidths);
        Color colorCeldas = new Color(88, 21, 28);

        listadoUsuarios.forEach(usuario -> {
            PdfPCell cUsuario = new PdfPCell(new Phrase(usuario.getId().toString(), fTUsuarios));
            cUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
            cUsuario.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cUsuario.setBorderColor(colorCeldas);
            tablaUsuarios.addCell(cUsuario);
            cUsuario = new PdfPCell(new Phrase(usuario.getUsername(), fTUsuarios));
            cUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
            cUsuario.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cUsuario.setBorderColor(colorCeldas);
            tablaUsuarios.addCell(cUsuario);
            cUsuario = new PdfPCell(new Phrase(usuario.getPassword(), fTUsuarios));
            cUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
            cUsuario.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cUsuario.setBorderColor(colorCeldas);
            tablaUsuarios.addCell(cUsuario);
            cUsuario = new PdfPCell(new Phrase(usuario.getEnabled().toString(), fTUsuarios));
            cUsuario.setHorizontalAlignment(Element.ALIGN_CENTER);
            cUsuario.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cUsuario.setBorderColor(colorCeldas);
            tablaUsuarios.addCell(cUsuario);

            PdfPCell cRol = new PdfPCell();
            cRol.setHorizontalAlignment(Element.ALIGN_CENTER);
            cRol.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cRol.setBorderColor(colorCeldas);
            StringBuilder nRol = new StringBuilder();
            for (Roles rol : usuario.getRoles()) nRol.append(rol.getRol()).append(", ");
            if (nRol.length() > 2) nRol.setLength(nRol.length() - 2);
            cRol.addElement(new Phrase(nRol.toString(), fTUsuarios));
            tablaUsuarios.addCell(cRol);
        });

        document.add(tablaTitulo);
        document.add(tablaTColumns);
        document.add(tablaUsuarios);

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
