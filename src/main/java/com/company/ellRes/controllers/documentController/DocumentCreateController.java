package com.company.ellRes.controllers.documentController;


import com.company.ellRes.dataService.dataToday;
import com.company.ellRes.domian.Document;
import com.company.ellRes.domian.User;
import com.company.ellRes.errorConfig.*;
import com.company.ellRes.fileService.saveFileDocument;
import com.company.ellRes.fileService.saveImageDocument;
import com.company.ellRes.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("createDocument")
public class DocumentCreateController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private saveImageDocument saveImageDocument;

    @Autowired
    private saveFileDocument saveFileDocument;

    @Autowired
    private dataToday dataToday;


    @GetMapping
    public String create(){
        return "document/createDocument";
    }

    @PostMapping
    public String save(
            @RequestParam Map<String, String> form,
            @AuthenticationPrincipal User autor,
            @RequestParam("file") MultipartFile file,
            Model model
    )throws IOException, ParseException {

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(form.get("number"),new int[] {1,2});
        if (v != 0){
            model.addAttribute("error", "Поле номер документа " + errorValue.value(v));
            model.addAttribute("coment", form.get("coment"));
            return "document/createDocument";
        }

        v = errorController.parse(form.get("coment"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле коментар " + errorValue.value(v));
            model.addAttribute("number", form.get("number"));
            return "document/createDocument";
        }

        v = errorController.parse(file.getOriginalFilename(), new int[] {1,2,6});
        if (v != 0){
            model.addAttribute("error", "Поле файл " + errorValue.value(v));
            model.addAttribute("number", form.get("number"));
            model.addAttribute("coment", form.get("coment"));
            return "document/createDocument";
        }


        String nameFile = saveFileDocument.uploadDocument(file);

        Document document = new Document();
        document.setNumber(form.get("number"));
        document.setComent(form.get("coment"));
        document.setTelegram(false);
        document.setAuthor(autor);
        document.setName(nameFile);
        document.setDate(dataToday.dateToday());
        document.setResolution(false);
        documentService.save(document);

        return "menu";
    }
}
