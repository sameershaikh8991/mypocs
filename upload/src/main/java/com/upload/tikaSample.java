package com.upload;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class tikaSample {
    public static void main(String[] args) throws TikaException, IOException, SAXException {
//        File file = new File("C:/Users/user/Desktop/springbootproject/upload/src/main/resources/upload/s1.pdf");
        File file = new File("C:/Users/user/Desktop/springbootproject/upload/src/main/java/com/upload/data/sample.txt");

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputstream, handler, metadata, context);
        System.out.println("File content : " + handler.toString());


//        Tika tika = new Tika();
//        FileInputStream inputstream = new FileInputStream(file);
//        String content = tika.parseToString(inputstream);
//        System.out.println("content"+content);


        Tika tika = new Tika();

        try {
            // Extract text content from the document
            String content = tika.parseToString(file);
            System.out.println("Extracted Content:\n" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
