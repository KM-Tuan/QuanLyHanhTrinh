/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kmt.service.FoodOrderItemService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiFoodOrderItemController {

    @Autowired
    private FoodOrderItemService foodOrderSer;

    @GetMapping("/statistics/most-ordered")
    public List<Object[]> getMostOrderedByJourney() {
        return foodOrderSer.getMostOrderedByJourney();
    }

    @GetMapping("/statistics/most-ordered/csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"most_ordered.csv\"");

        List<Object[]> data = foodOrderSer.getMostOrderedByJourney();

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            // Header
            writer.writeNext(new String[]{"Journey Name", "Food Name", "Total Ordered"});

            // Data
            for (Object[] row : data) {
                writer.writeNext(new String[]{
                    row[0].toString(),
                    row[1].toString(),
                    row[2].toString()
                });
            }
        }
    }

    @GetMapping("/statistics/most-ordered/pdf")
    public void exportPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"most_ordered.pdf\"");

        List<Object[]> data = foodOrderSer.getMostOrderedByJourney();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Title
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Most Ordered Food by Journey", font);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Table
        PdfPTable table = new PdfPTable(3); // 3 columns
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 3, 2});

        // Header
        Stream.of("Journey Name", "Food Name", "Total Ordered").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(headerTitle, headFont));
            table.addCell(header);
        });

        // Data
        for (Object[] row : data) {
            table.addCell(row[0].toString());
            table.addCell(row[1].toString());
            table.addCell(row[2].toString());
        }

        document.add(table);
        document.close();
    }

}
