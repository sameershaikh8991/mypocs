package com.htmltopdf.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(){

        logger.info("create PDF stated");

        String title ="welcome to code";
        String content = "Hello sameer";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();


        PdfWriter.getInstance(document,out);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD,30);
        Paragraph titlePara = new Paragraph(title,titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        Font contentFont = FontFactory.getFont(FontFactory.COURIER_BOLD,17);
        Paragraph contentPara = new Paragraph(content,contentFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(contentPara);


        document.close();

        return  new ByteArrayInputStream(out.toByteArray());
    }
}
