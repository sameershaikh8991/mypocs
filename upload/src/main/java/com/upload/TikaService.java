package com.upload;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
//import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TikaService {

    public String extractText(InputStream inputStream) throws IOException, TikaException, SAXException {
        Parser parser = new AutoDetectParser();
//        BodyContentHandler handler = new BodyContentHandler();
//        Metadata metadata = new Metadata();
//        ParseContext parseContext = new ParseContext();
//
//        parser.parse(inputStream, handler, metadata, parseContext);
//
//        System.out.println("handler:"+handler);
//
//        return handler.toString();


        Tika tika = new Tika();
//        FileInputStream inputstream = new FileInputStream(file);
        System.out.println("-----------------");
        String data = tika.parseToString(inputStream);
        return data;
    }

    public  String extractTextImg(File file) throws TesseractException {

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("c:/Users/user/Desktop/tika/src/main/java/com/tika/tessdata");
            String result = tesseract.doOCR(file);
            return result;


    }
}
