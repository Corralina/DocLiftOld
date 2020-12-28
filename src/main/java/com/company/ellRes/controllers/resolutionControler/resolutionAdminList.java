package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.User;
import com.company.ellRes.service.ResolutionService;
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
@RequestMapping("/allResolutionList")
public class resolutionAdminList {

    @Autowired
    private ResolutionService resolutionService;


    @GetMapping
    public String adminList(
            @AuthenticationPrincipal User user,
            Model model
            ){
        String roles = user.getRoles().toString();
        if (roles.indexOf("ADMIN") > 0 || roles.indexOf("RECORTED") > 0){
            model.addAttribute("resolutions", resolutionService.allFinishTrue());
        }else{
            model.addAttribute("error","У Вас недостатньо прав на перегляд даної сторінки. Зверніться до адміністратора");
        }
        return "resolution/allResolutionList";
    }

    @PostMapping
    public String statusFilter(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ){

        String number = form.get("number");
        String agrees = form.get("agrees");
        String autor = form.get("autor");
        LocalDate dataStart = LocalDate.now();
        LocalDate dataStop = LocalDate.now();

        if (form.get("dateStart") != ""){
            dataStart = LocalDate.parse(form.get("dateStart"));
        }

        if (form.get("dateStop") != ""){
            dataStop = LocalDate.parse(form.get("dateStop"));
        }

        model.addAttribute("number", number);
        model.addAttribute("agrees", agrees);
        model.addAttribute("autor", autor);
        model.addAttribute("dateStart", form.get("dateStart"));
        model.addAttribute("dateStop", form.get("dateStop"));

        String roles = user.getRoles().toString();
        if (roles.indexOf("ADMIN") > 0 || roles.indexOf("RECORTED") > 0){
            if (form.get("dateStart") == "" && form.get("dateStop") == ""){
                model.addAttribute("resolutions", resolutionService.allFinishTrueFilter(number, agrees, autor));
            }else if (dataStart == dataStop){
                model.addAttribute("resolutions", resolutionService.allFinishTrueFilterOneDate(number, agrees, autor, dataStart));
            }else {
                model.addAttribute("resolutions", resolutionService.allFinishTrueFilterDate(number, agrees, autor, dataStart, dataStop));
            }

        }


        return "resolution/allResolutionList";

    }


}
