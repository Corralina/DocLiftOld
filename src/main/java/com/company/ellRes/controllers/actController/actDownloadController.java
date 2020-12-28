package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.DocumentActService;
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("actDownload")
public class actDownloadController {

    @Value("${upload.path.act.doc}")
    private String uploadPathDocument;

    @Autowired
    private com.company.ellRes.dataService.dataFile dataFile;

    @Autowired
    private com.company.ellRes.dataService.dataToday dataToday;

    @Autowired
    private DocumentActService documentActService;

    @Autowired
    private com.company.ellRes.fileService.saveFileDocument saveFileDocument;



    @GetMapping
    public String create(Model model) {

        return "act/actDownload";
    }

    @PostMapping
    public String sva(
            @RequestParam Map<String, String> form,
            @AuthenticationPrincipal User autor,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws ParseException, IOException {


        String nameFile = saveFileDocument.uploadDocx(file);
        //convert .docx to HTML string
        InputStream in= new FileInputStream(new File(this.uploadPathDocument + "/" + dataFile.geteTodayYer() + "/" + nameFile));
        XWPFDocument document = new XWPFDocument(in);


        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("C:\\DocumentLift\\files\\document\\іew.html")));

        OutputStream out = new ByteArrayOutputStream();


        XHTMLConverter.getInstance().convert(document, out, options);
        String html=out.toString();
        System.out.println(html);
        nameFile = saveFileDocument.uploadHTMLDocument(html);

        DocumentAct documentAct = new DocumentAct();
        documentAct.setDate(dataToday.dateToday());
        documentAct.setTime(dataToday.timeToday());
        documentAct.setAct(false);
        documentAct.setDescription(form.get("description"));
        documentAct.setAuthor(autor);

        documentAct.setName(nameFile);

        documentActService.save(documentAct);



        return "menu";
    }

}