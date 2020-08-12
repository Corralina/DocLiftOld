package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.dataService.dataToday;
import com.company.ellRes.domian.*;
import com.company.ellRes.service.PerformerService;
import com.company.ellRes.service.ResolutionService;
import com.company.ellRes.service.StaticService;
import com.company.ellRes.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Iterator;

@Controller
public class resolutionAgreesController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private PerformerService performerService;

    @Autowired
    private StaticService staticService;

    @Autowired
    private ResolutionService resolutionService;


    private dataToday dataToday = new dataToday();

    @PostMapping("resolutionVisa")
    public String visa(
            @RequestParam("resolution") Resolution resolution,
            @AuthenticationPrincipal User user,
            Model model
    ) throws ParseException {
        if (!resolution.getAgrees().getUsername().equals(user.getUsername()) && !user.isAdmin()){
            model.addAttribute("error","Не достатньо прав!");
            return "redirect:resolution/" + resolution.getId();

        }
        if(resolution.getStatus().getFinish()){
            model.addAttribute("error","Резолюцію було завізовано" + resolution.getDate());
            return "redirect:resolution/" + resolution.getId();

        }
        if (resolution.getStatus().getRevers()){
            model.addAttribute("error","Резолюція знаходиться на редагувані!");
            return "redirect:resolution/" + resolution.getId();

        }

        Visa visa = new Visa();
        visa.setAgrees(resolution.getAgrees().getInformation().getIndividual().getInitials());
        visa.setData(dataToday.dateToday());
        visa.setPosition(resolution.getAgrees().getInformation().getIndividual().getPost());
        visa = visaService.save(visa);

        Iterable<Performer> performers = performerService.byResolution(resolution);

        Visa finalVisa = visa;
        performers.forEach(performer ->{
                    Static stat = new Static();

                    stat.setDoer(performer.getComent());
                    stat.setInitials((performer.getUser().getInformation().getIndividual().getInitials()));
                    stat.setVisa(finalVisa);
                    staticService.save(stat);
                }
        );

        resolution.setVisa(visa);
        resolution.getStatus().setFinish(true);
        resolutionService.save(resolution);


        return "redirect:resolutionShow/" + resolution.getId();
    }



}
