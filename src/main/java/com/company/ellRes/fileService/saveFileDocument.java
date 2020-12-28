package com.company.ellRes.fileService;


import com.company.ellRes.dataService.dataFile;
import com.company.ellRes.domian.DocumentAct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class saveFileDocument {

    @Value("${upload.path.document}")
    private String uploadPathDocument;

    @Value("${upload.path.act.doc}")
    private String uploadPathDocx;

    @Value("${upload.path.act}")
    private String uploadPathAct;

    @Autowired
    private dataFile dataFile;


    public String uploadDocument(MultipartFile file) throws IOException {
        File uploadDir = new File(this.uploadPathDocument + "/" + dataFile.geteTodayYer());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(this.uploadPathDocument + "/" + dataFile.geteTodayYer() + "/" + resultFileName));
        return resultFileName;
    }

    public String uploadDocx(MultipartFile file) throws IOException {
        File uploadDir = new File(this.uploadPathDocx + "/" + dataFile.geteTodayYer());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(this.uploadPathDocx + "/" + dataFile.geteTodayYer() + "/" + resultFileName));
        return resultFileName;
    }

    public String uploadHTMLDocument(String text) throws IOException {
        File uploadDir = new File(this.uploadPathAct + "/" + dataFile.geteTodayYer());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + ".html";

        File f = new File(this.uploadPathAct + "/" + dataFile.geteTodayYer() + "/" + resultFileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        int k = text.indexOf("<html><body>");
        if (k < 0){
            bw.write("<html><body>");
        }
        bw.write(text);
        if (k < 0){
            bw.write("</body></html>");
        }

        bw.close();

        return resultFileName;
    }

    public String uploadHTMLText(DocumentAct documentAct){

        String content = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(this.uploadPathAct + "/" + documentAct.getDate().getYear() + "/" + documentAct.getDate().getMonthValue() + "/" + documentAct.getName()));
            String str;
            while ((str = in.readLine()) != null) {
                content +=str;
            }
            in.close();
        } catch (IOException e) {
            content = "Fille don`t found!";
        }

        return content;

    }

    public boolean editHTMLDocument(String text, DocumentAct documentAct) throws IOException {
        try {
            File f = new File(this.uploadPathAct + "/" + documentAct.getDate().getYear() + "/" + documentAct.getDate().getMonthValue() + "/" + documentAct.getName());
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            int k = text.indexOf("<html><body>");
            if (k < 0) {
                bw.write("<html><body>");
            }
            bw.write(text);
            if (k < 0) {
                bw.write("</body></html>");
            }

            bw.close();

            return true;
        }catch (IOException e) {
            return false;
        }
    }



}
