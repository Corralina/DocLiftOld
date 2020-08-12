package com.company.ellRes.controllers.documentController;


import com.company.ellRes.domian.User;
import com.company.ellRes.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/documentArchive")
public class DocumentArchiveController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public String documentList(
            Model model
    ){
        model.addAttribute("documents",documentService.all());
        return "document/documentArchive";
    }

    @PostMapping("filter")
    public String filter(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) {

        String number = form.get("number");
        String autor = form.get("autor");
        LocalDate dataStart = LocalDate.now();
        LocalDate dataStop = LocalDate.now();


        if (form.get("dateStart") != "") {
            dataStart = LocalDate.parse(form.get("dateStart"));
        }

        if (form.get("dateStop") != "") {
            dataStop = LocalDate.parse(form.get("dateStop"));
        }

        model.addAttribute("number", number);
        model.addAttribute("autor", autor);
        model.addAttribute("dateStart", form.get("dateStart"));
        model.addAttribute("dateStop", form.get("dateStop"));


        if (form.get("dateStart") == "" && form.get("dateStop") == "") {
            model.addAttribute("documents", documentService.allFilter(number, autor));
        } else if (dataStart == dataStop) {
            model.addAttribute("documents", documentService.allFilterOneDate(number, autor, dataStart));
        } else {
            model.addAttribute("documents", documentService.allFilterDate(number, autor, dataStart, dataStop));
        }

        return "document/documentArchive";

    }
}
