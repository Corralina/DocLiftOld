package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.Performer;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.PerformerService;
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
@RequestMapping("/resolutionList")
public class resolutionListController {

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private PerformerService performerService;


    @GetMapping
    public String list(
            @AuthenticationPrincipal User user,
            Model model){

            model.addAttribute("resolutions", performerService.allUser(user));

        return "resolution/resolutionList";
    }

    @GetMapping("status")
    public String listAgrees(
            @AuthenticationPrincipal User user,
            Model model
    ){
        String roles = user.getRoles().toString();
        if (roles.indexOf("ADMIN") > 0){
            model.addAttribute("resolutions", resolutionService.allFinishFalse());
        }else if(roles.indexOf("TABLIN") > 0 && roles.indexOf("CONFIRM") > 0){
            model.addAttribute("resolutions", resolutionService.combo(user));
        }else if(roles.indexOf("TABLIN") > 0 && roles.indexOf("CONFIRM") < 0){
            model.addAttribute("resolutions", resolutionService.allFilling(user));
        }else if(roles.indexOf("TABLIN") < 0 && roles.indexOf("CONFIRM") > 0){
            model.addAttribute("resolutions", resolutionService.allAgrees(user));


        }



        return "resolution/resolutionStatusList";
    }

    @PostMapping("filter")
    public String filter(
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

        Iterable<Performer> resolutions;

        if (form.get("dateStart") == "" && form.get("dateStop") == ""){
            resolutions =  performerService.filter(user, number, agrees, autor);
        }else if (dataStart == dataStop){
            resolutions = performerService.filterOneDate(dataStart, user, number, agrees, autor);

        }else {
            resolutions = performerService.filterData(dataStart, dataStop, user, number, agrees, autor);
        }

        model.addAttribute("resolutions", resolutions);


        return "resolution/resolutionList";

    }
    @PostMapping("statusFilter")
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
        if (roles.indexOf("ADMIN") > 0){
            if (form.get("dateStart") == "" && form.get("dateStop") == ""){
                model.addAttribute("resolutions", resolutionService.allFinishFalseFilter(number, agrees, autor));
            }else if (dataStart == dataStop){
                model.addAttribute("resolutions", resolutionService.allFinishFalseFilterOneDate(number, agrees, autor, dataStart));
            }else {
                model.addAttribute("resolutions", resolutionService.allFinishFalseFilterDate(number, agrees, autor, dataStart, dataStop));
            }

        }else if(roles.indexOf("TABLIN") > 0 && roles.indexOf("CONFIRM") > 0){
            if (form.get("dateStart") == "" && form.get("dateStop") == ""){
                model.addAttribute("resolutions", resolutionService.comboFilter(user, number, agrees, autor));
            }else if (dataStart == dataStop){
                model.addAttribute("resolutions", resolutionService.comboFilterOneDate(user, number, agrees, autor, dataStart));
            }else {
                model.addAttribute("resolutions", resolutionService.comboFilterDate(user, number, agrees, autor, dataStart, dataStop));
            }

        }else if(roles.indexOf("TABLIN") > 0 && roles.indexOf("CONFIRM") < 0){
            if (form.get("dateStart") == "" && form.get("dateStop") == ""){
                model.addAttribute("resolutions", resolutionService.fillingFilter(user, number, agrees, autor));
            }else if (dataStart == dataStop){
                model.addAttribute("resolutions", resolutionService.fillingFilterOneDate(user, number, agrees, autor, dataStart));
            }else {
                model.addAttribute("resolutions", resolutionService.fillingFilterDate(user, number, agrees, autor, dataStart, dataStop));
            }

        }else if(roles.indexOf("TABLIN") < 0 && roles.indexOf("CONFIRM") > 0) {
            if (form.get("dateStart") == "" && form.get("dateStop") == ""){
                model.addAttribute("resolutions", resolutionService.agreesFilter(user, number, agrees, autor));
            }else if (dataStart == dataStop){
                model.addAttribute("resolutions", resolutionService.agreesFilterOneDate(user, number, agrees, autor,dataStart));
            }else {
                model.addAttribute("resolutions", resolutionService.agreesFilterDate(user, number, agrees, autor, dataStart, dataStop));
            }

        }

        return "resolution/resolutionStatusList";

    }


}
