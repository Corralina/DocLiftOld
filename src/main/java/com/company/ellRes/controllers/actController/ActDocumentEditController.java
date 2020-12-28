package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.DocumentAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.ActService;
import com.company.ellRes.service.DocumentActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("actDocumentEdit")
public class ActDocumentEditController {


    @Autowired
    private com.company.ellRes.fileService.saveFileDocument saveFileDocument;

    @Autowired
    private ActService actService;

    @Autowired
    private DocumentActService documentActService;




    @GetMapping("{document}")
    public String edit(
            @AuthenticationPrincipal User user,
            @PathVariable DocumentAct document,
            Model model
    ){

        if (!document.isAct()) {
            String textDocument = saveFileDocument.uploadHTMLText(document);

            model.addAttribute("textDocument", saveFileDocument.uploadHTMLText(document));
            model.addAttribute("document", document);

            return "actDocumentEdit";
        }
        model.addAttribute("error","На даний документ вже накладено підписи");
        model.addAttribute("documents", documentActService.listActFalse(user));
        return "act/actList";
    }


    @PostMapping("{document}")
    public String editSave(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            @PathVariable DocumentAct document,
            Model model
    ) throws IOException {


        if (!document.isAct()) {

            if (document.getAuthor().getUsername().equals(user.getUsername())) {
                if (saveFileDocument.editHTMLDocument(form.get("textAct"), document)) {
                    document.setAuthor(user);
                    documentActService.save(document);
                } else {
                    model.addAttribute("error", "Виникла помилка ппри редагувані файлу. Внесені зміни не були збережені. Зверніться до адміністратора");
                }
            } else {
                model.addAttribute("error", "Ви не маєте можливості редагувати файл");
            }
        }else {
            model.addAttribute("error", "Ви не маєте можливості редагувати файл, так як на даний файл вже складені підписи");
        }

        model.addAttribute("documents", documentActService.listActFalse(user));

        return "act/actList";

    }
}
