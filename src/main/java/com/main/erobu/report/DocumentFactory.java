package com.main.erobu.report;

import com.itextpdf.text.DocumentException;
import com.main.erobu.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

@Service
public class DocumentFactory {

    public static final String DEFAULT_PDF_REPORT_PATH = "src/main/resources/pdf/tickets.pdf";



    public File createOnlineTickets(String format, Set<OrderItemDTO> orderItemDTOList) throws FileNotFoundException, DocumentException {
        if(format==null){
            return null;
        }
        else if(format.equalsIgnoreCase("PDF")){
            return new PdfBuilder().createOnlineTickets(DEFAULT_PDF_REPORT_PATH,orderItemDTOList);
        }
        return null;
    }
}
