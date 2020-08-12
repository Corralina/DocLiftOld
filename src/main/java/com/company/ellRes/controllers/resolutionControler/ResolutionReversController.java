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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

@Controller
public class ResolutionReversController {

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private ReversService reversService;

    @Autowired
    private PerformerService performerService;

    private com.company.ellRes.dataService.dataToday dataToday = new dataToday();

    @PostMapping("resolutionRevers")
    public String revers(
            @RequestParam("resolution") Resolution resolution,
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
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
            model.addAttribute("error","Резолюція вже знаходиться на редагувані!");
            return "redirect:resolution/" + resolution.getId();

        }

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();
        int v = errorController.parse(form.get("coment"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле коментар " + errorValue.value(v));
            return "redirect:resolution/" + resolution.getId();
        }

        resolution.getStatus().setRevers(true);
        resolutionService.save(resolution);

        Revers revers = new Revers();
        revers.setComent(form.get("coment"));
        revers.setDate(dataToday.dateToday());
        revers.setRev(false);
        revers.setTelegram(false);
        revers.setResolution(resolution);
        reversService.save(revers);

        return "redirect:resolution/" + resolution.getId();

    }
}
