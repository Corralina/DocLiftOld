package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.*;
import com.company.ellRes.errorConfig.errorController;
import com.company.ellRes.errorConfig.errorValue;
import com.company.ellRes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/resolutionEdit")
public class resolutionEditController {

    @Autowired
    private PerformerService performerService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ReversService reversService;

    @Autowired
    private ResolutionService resolutionService;


    @PostMapping
    public String edit(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Resolution upate,
            @RequestParam("revers") Revers revers,
            Model model
    ){

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(upate.getComent(), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле коментар " + errorValue.value(v));
            return "redirect:resolution/" + revers.getResolution().getId();
        }

        int i = 1;
        for(String key : form.keySet()){
            if (key.indexOf("perfo")>=0){
                v = errorController.parse(form.get(key.replace("performs", "note")), new int[] {1});
                if (v != 0){
                    model.addAttribute("error", "Поле призначення " + errorValue.value(v));
                    return "redirect:resolution/" + revers.getResolution().getId();
                }
                i++;
            }
        }



        revers.setRev(true);
        reversService.save(revers);

        Status status = revers.getResolution().getStatus();
        status.setRevers(false);
        status.setTelegram(false);
        statusService.save(status);

        Resolution resolution = revers.getResolution();
        resolution.setComent(upate.getComent());
        resolution.setAgrees(upate.getAgrees());
        resolutionService.save(resolution);


        for (Performer performer : performerService.byResolution(revers.getResolution())){
            performerService.drop(performer);
        }

        i = 1;
        for(String key : form.keySet()){
            if (key.indexOf("perfo")>=0){
                Performer performer = new Performer();
                performer.setResolution(revers.getResolution());
                performer.setUser(userService.findByUsername(form.get(key)));
                performer.setComent(form.get(key.replace("performs", "note")));
                performerService.save(performer);
                i++;
            }
        }

        return "redirect:resolution/" + resolution.getId();
    }
}
