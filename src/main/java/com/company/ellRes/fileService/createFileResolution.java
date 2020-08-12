package com.company.ellRes.fileService;

import com.company.ellRes.dataService.dataFile;
import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.Static;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class createFileResolution {

    private String uploadPathResolution = "C:\\DocumentLift\\config\\resolve\\";
    private String uploadPathCaption = "C:\\DocumentLift\\config\\caption\\";
    private String uploadFileSystem = "C:\\DocumentLift\\system\\";

//    @Value("${upload.path.caption}")
//    private String uploadPathCaption;
//    @Value("${upload.path.resolve}")
//    private String uploadPathResolution;


    private dataFile dataFile = new dataFile();

    static int FONT_SIZE_SMALL = 10;
    static int FONT_SIZE_BIG = 12;



    public String formRes(Resolution resolution, Iterable<Static> statics, Boolean caption) throws IOException, DocumentException {

        File uploadDir = new File(this.uploadPathResolution + "\\" + dataFile.geteTodayYer());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Document document = new Document(PageSize.A6);
        BaseFont bfB = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontBig = new Font(bfB, FONT_SIZE_BIG);

        BaseFont bfS = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font fontSmall = new Font(bfS, FONT_SIZE_SMALL);

        String uuidFile = UUID.randomUUID().toString();
        PdfWriter.getInstance(document,
                new FileOutputStream(this.uploadPathResolution + "\\" + dataFile.geteTodayYer() + "\\" + uuidFile + ".pdf"));

        document.open();


        //post
        if (resolution.getVisa() != null) {
            if (resolution.getVisa().getPosition() != null) {
                Paragraph amount = new Paragraph();
                amount.setFont(fontBig);
                amount.setAlignment(Element.ALIGN_CENTER);
                amount.add(new Chunk(resolution.getVisa().getPosition()));
                document.add(amount);
            }
        }
        Paragraph sud1 = new Paragraph();
        sud1.setFont(fontBig);
        sud1.setAlignment(Element.ALIGN_CENTER);
        sud1.add(new Chunk("Сьомого апеляційного"));
        document.add(sud1);

        Paragraph sud2 = new Paragraph();
        sud2.setFont(fontBig);
        sud2.setAlignment(Element.ALIGN_CENTER);
        sud2.setSpacingAfter(8);
        sud2.add(new Chunk("адміністративного суду"));
        document.add(sud2);


        // Black line
        Image stamp = Image.getInstance(uploadFileSystem + "black.jpg");
        stamp.scaleAbsolute(250,2);
        stamp.setAlignment(Element.ALIGN_CENTER);
        document.add(stamp);

        Image stamp2 = Image.getInstance(uploadFileSystem + "black.jpg");
        stamp2.scaleAbsolute(250,5);
        stamp2.setAlignment(Element.ALIGN_CENTER);
        document.add(stamp2);

        //performers
        statics.forEach(stat-> {
            Paragraph perf = new Paragraph();
            perf.setFont(fontSmall);
            perf.setAlignment(Element.ALIGN_CENTER);
            perf.setSpacingAfter(4);
            perf.add(new Chunk(stat.getInitials()));
            try {
                document.add(perf);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            if (stat.getDoer() != null) {
                Paragraph com = new Paragraph();
                com.setFont(fontSmall);
                com.setAlignment(Element.ALIGN_CENTER);
                com.setSpacingAfter(9);
                com.add(new Chunk(stat.getDoer()));
                try {
                    document.add(com);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

        //caption
        if (caption){
            Image cap = Image.getInstance(uploadPathCaption + resolution.getAgrees().getInformation().getIndividual().getCaption());
            cap.scaleAbsolute(100,100);
            cap.setAlignment(Element.ALIGN_CENTER);
            cap.setSpacingAfter(10);
            document.add(cap);
        }else {
            Paragraph cap = new Paragraph();
            cap.setSpacingAfter(100);
            document.add(cap);

        }


        //agress
        Paragraph agees = new Paragraph();
        agees.setFont(fontBig);
        agees.setAlignment(Element.ALIGN_RIGHT);
        if (resolution.getVisa() != null) {
            agees.add(new Chunk(resolution.getVisa().getAgrees()));
        }else {
            agees.add(new Chunk(resolution.getAgrees().getInformation().getIndividual().getInitials()));
        }
        document.add(agees);

        //agress
        Paragraph date = new Paragraph();
        date.setFont(fontBig);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.add(new Chunk(String.valueOf(resolution.getDate())));

        document.add(date);


        document.close();

        return uuidFile + ".pdf";
    }
}
