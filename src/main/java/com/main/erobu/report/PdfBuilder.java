package com.main.erobu.report;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.main.erobu.dto.OrderItemDTO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;


public class PdfBuilder {


    public File createOnlineTickets(String filePath, Set<OrderItemDTO> orderItemDTOList) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        document.setMargins(36, 72, 108, 180);
        document.setMarginMirroring(true);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
                "Thank you for buying from us!"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            document.add(new Paragraph(
                    currentUserName.toUpperCase() + " here are your tickets:"));
        }
        // step 5


        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("ID order", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Event name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Organised by ", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Location", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Unit price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total price", font));
        table.addCell(cell);

        for (OrderItemDTO orderItemDTO : orderItemDTOList) {
            table.addCell(orderItemDTO.getId().toString());
            table.addCell(orderItemDTO.getIdOrder().toString());
            table.addCell(orderItemDTO.getTicketDTO().getEventDTO().getName());
            table.addCell(orderItemDTO.getTicketDTO().getEventDTO().getEditorDTO().getName());
            table.addCell(orderItemDTO.getTicketDTO().getEventDTO().getDateStart().toString());
            table.addCell(orderItemDTO.getTicketDTO().getEventDTO().getLocation());
            table.addCell(orderItemDTO.getQuantity().toString());
            table.addCell(orderItemDTO.getTicketDTO().getPrice().toString());
            table.addCell(orderItemDTO.getTotalPrice().toString());
        }

        document.add(table);
        document.add(new Paragraph("Events Online, our contact email adress:events.diversity.helpdesk@gmail.com"));
        document.close();

        return new File(filePath);
    }


}
