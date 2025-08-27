/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kmt.pojo.FoodOrder;
import com.kmt.service.FoodOrderService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiFoodOrderController {

    @Autowired
    private FoodOrderService oderSer;

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FoodOrder> checkout(@RequestBody Map<String, Object> payload) {
        Integer userId = (Integer) payload.get("userId");
        String journeyName = (String) payload.get("journeyName");
        List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");

        FoodOrder saved = oderSer.createOrder(items, userId, journeyName);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<?> getFoodOrdersByUser(@PathVariable("userId") Integer userId) {
        List<FoodOrder> orders = oderSer.getOrderByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/statistics/day")
    public List<Object[]> getRevenueByDay() {
        return oderSer.getTotalRevenueByDay();
    }

    @GetMapping("/statistics/month")
    public List<Object[]> getRevenueByMonth() {
        return oderSer.getTotalRevenueByMonth();
    }

    @GetMapping("/statistics/year")
    public List<Object[]> getRevenueByYear() {
        return oderSer.getTotalRevenueByYear();
    }

    /**
     * ================= CSV ================= *
     */
    @GetMapping("/statistics/day/csv")
    public void exportRevenueByDayCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_day.csv\"");

        List<Object[]> data = oderSer.getTotalRevenueByDay();

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            writer.writeNext(new String[]{"Day", "Total Revenue"});
            for (Object[] row : data) {
                writer.writeNext(new String[]{row[0].toString(), row[1].toString()});
            }
        }
    }

    @GetMapping("/statistics/month/csv")
    public void exportRevenueByMonthCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_month.csv\"");

        List<Object[]> data = oderSer.getTotalRevenueByMonth();

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            writer.writeNext(new String[]{"Year", "Month", "Total Revenue"});
            for (Object[] row : data) {
                writer.writeNext(new String[]{row[0].toString(), row[1].toString(), row[2].toString()});
            }
        }
    }

    @GetMapping("/statistics/year/csv")
    public void exportRevenueByYearCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_year.csv\"");

        List<Object[]> data = oderSer.getTotalRevenueByYear();

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            writer.writeNext(new String[]{"Year", "Total Revenue"});
            for (Object[] row : data) {
                writer.writeNext(new String[]{row[0].toString(), row[1].toString()});
            }
        }
    }

    /**
     * ================= PDF ================= *
     */
    @GetMapping("/statistics/day/pdf")
    public void exportRevenueByDayPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_day.pdf\"");

        List<Object[]> data = oderSer.getTotalRevenueByDay();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph title = new Paragraph("Revenue by Day", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 2});

        // Header
        Stream.of("Day", "Total Revenue").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPhrase(new Phrase(headerTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(header);
        });

        // Data
        for (Object[] row : data) {
            table.addCell(row[0].toString());
            table.addCell(row[1].toString());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/statistics/month/pdf")
    public void exportRevenueByMonthPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_month.pdf\"");

        List<Object[]> data = oderSer.getTotalRevenueByMonth();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph title = new Paragraph("Revenue by Month", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 2, 2});

        Stream.of("Year", "Month", "Total Revenue").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPhrase(new Phrase(headerTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(header);
        });

        for (Object[] row : data) {
            table.addCell(row[0].toString());
            table.addCell(row[1].toString());
            table.addCell(row[2].toString());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/statistics/year/pdf")
    public void exportRevenueByYearPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"revenue_by_year.pdf\"");

        List<Object[]> data = oderSer.getTotalRevenueByYear();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph title = new Paragraph("Revenue by Year", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 2});

        Stream.of("Year", "Total Revenue").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPhrase(new Phrase(headerTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(header);
        });

        for (Object[] row : data) {
            table.addCell(row[0].toString());
            table.addCell(row[1].toString());
        }

        document.add(table);
        document.close();
    }

}
