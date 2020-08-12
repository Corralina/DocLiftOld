package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.dataService.dataToday;
import com.company.ellRes.domian.*;
import com.company.ellRes.errorConfig.errorController;
import com.company.ellRes.errorConfig.errorValue;
import com.company.ellRes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/resolutionCreate")
public class resolutionCreateController {

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PerformerService performerService;

    private dataToday dataToday = new dataToday();




    @GetMapping("{document}")
    public String userEditForm(
            @PathVariable Document document,
            Model model
    ){

        model.addAttribute("document", document);
        model.addAttribute("users",userService.allUser());
        model.addAttribute("confirms",userService.userConfirms(Collections.singleton(Role.CONFIRM)));

        return "resolution/resolutionCreate";

    }

    @PostMapping
    public String saveRes(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Resolution resolution,
            Model model
    ) throws ParseException {

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(form.get("coment"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле коментар " + errorValue.value(v));
            model.addAttribute("document", resolution.getDocument());
            model.addAttribute("users",userService.allUser());
            model.addAttribute("confirms",userService.userConfirms(Collections.singleton(Role.CONFIRM)));
            return "resolution/resolutionCreate";
        }
        int i = 1;
        for(String key : form.keySet()){
            if (key.indexOf("perfo")>=0){
                v = errorController.parse(form.get(key.replace("performs", "note")), new int[] {1});
                if (v != 0){
                    model.addAttribute("error", "Поле призначення " + errorValue.value(v));
                    model.addAttribute("document", resolution.getDocument());
                    model.addAttribute("users",userService.allUser());
                    model.addAttribute("confirms",userService.userConfirms(Collections.singleton(Role.CONFIRM)));
                    return "resolution/resolutionCreate";
                }
                i++;
            }
        }

        Status status = new Status();
        status.setFinish(false);
        status.setRevers(false);
        status.setTelegram(false);
        status.setDone(false);
        status = statusService.save(status);

        resolution.setStatus(status);
        resolution.setDate(dataToday.dateToday());
        resolution.setFilling(user);
        resolution = resolutionService.save(resolution);

        Document document = resolution.getDocument();
        document.setResolution(true);
        documentService.save(document);

        i = 1;
        for(String key : form.keySet()){
            if (key.indexOf("perfo")>=0){
                Performer performer = new Performer();
                performer.setResolution(resolution);
                performer.setUser(userService.findByUsername(form.get(key)));
                performer.setComent(form.get(key.replace("performs", "note")));
                performerService.save(performer);
                i++;
            }
        }

        return "redirect:documentList";
    }


}
