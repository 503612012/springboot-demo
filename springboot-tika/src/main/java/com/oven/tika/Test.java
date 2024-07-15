package com.oven.tika;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;

public class Test {

    public static void main(String[] args) throws Exception {
        // to string
        Tika tika = new Tika();
        String text1 = tika.parseToString(new File("/Users/xxx/Documents/xxx.pdf"));
        String text2 = tika.parseToString(new File("/Users/xxx/Documents/xxx.xlsx"));
        String text3 = tika.parseToString(new File("/Users/xxx/Documents/xxx.docx"));
        System.out.println(text1);
        System.out.println(text2);
        System.out.println(text3);

        // to html
        InputStream input = Files.newInputStream(new File("/Users/xxx/Documents/xxx.pdf").toPath());
        Parser parser = new PDFParser();

        StringWriter sw = new StringWriter();
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler handler = factory.newTransformerHandler();
        handler.getTransformer().setOutputProperty(OutputKeys.ENCODING, "utf-8");
        handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "html");
        handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
        handler.setResult(new StreamResult(sw));

        Metadata metadata = new Metadata();
        metadata.add(Metadata.CONTENT_TYPE, "text/html;charset=utf-8");
        parser.parse(input, handler, metadata, new ParseContext());
        System.out.println(sw);
    }

}
