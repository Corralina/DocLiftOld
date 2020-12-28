package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.DocumentActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("actCreate")
public class actCreateController {

    @Autowired
    private com.company.ellRes.dataService.dataToday dataToday;

    @Autowired
    private DocumentActService documentActService;

    @Autowired
    private com.company.ellRes.fileService.saveFileDocument saveFileDocument;



    @GetMapping
    public String create(Model model) {

        return "act/actCreate";
    }

    @PostMapping
    public String sva(
            @RequestParam Map<String, String> form,
            @AuthenticationPrincipal User autor,
            Model model
    ) throws ParseException, IOException {

        DocumentAct documentAct = new DocumentAct();
        documentAct.setDate(dataToday.dateToday());
        documentAct.setTime(dataToday.timeToday());
        documentAct.setAct(false);
        documentAct.setAuthor(autor);
        documentAct.setDescription(form.get("description"));
        documentAct.setName(saveFileDocument.uploadHTMLDocument(form.get("textAct")));

        documentActService.save(documentAct);

        return "menu";
    }

}