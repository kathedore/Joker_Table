package com.itextpdf.jumpstart;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.numbering.GeorgianNumbering;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;


public class Hello_world {
    public static final String DEST = "result/chpt1/Hello_world.pdf";
    public static final String FONT[] = {"src/main/resources/bpg_arial_2009.ttf"};

    public static void main(String [] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        System.out.println("შეიყვანეთ მოთამაშის სახელები");
        Scanner nm=new Scanner(System.in);
        new Hello_world().createPdf(DEST, nm.next(), nm.next(), nm.next(),nm.next());
    }

    public void createPdf(String destination, String nm1, String nm2, String nm3, String nm4) throws IOException{
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);
        PdfFont freeUnicode = PdfFontFactory.createFont(FONT[0], PdfEncodings.IDENTITY_H,false);
        doc.setFont(freeUnicode);
        Scanner sc=new Scanner(System.in);
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        table.setFontScript(Character.UnicodeScript.GEORGIAN);
        table.addCell(new Paragraph("სახელი/რიგება"));
        table.addCell(new Paragraph(nm1)).setTextAlignment(TextAlignment.CENTER);
        table.addCell(nm2);
        table.addCell(nm3);
        table.addCell(nm4);

        for (int i = 0; i < 8; i++) {
            String s = String.valueOf(i+1);
            table.addCell("რიგება"+""+s).setBold().setItalic();
            System.out.println("შეიყვანეთ"+" "+s+" "+"რიგების ქულები");
            //long str = System.currentTimeMillis();
            for(int j=0;j<4;j++){
                int scor= sc.nextInt();
               // long sec = System.currentTimeMillis();
                if(scor<900&& scor>0) {
                    String score = String.valueOf(scor);
                    //System.out.println(score);
                    table.addCell(new Paragraph(score)).setBold();
                }
                else table.addCell("");
            }
        }

        doc.add(table);
        doc.close();
    }
}

