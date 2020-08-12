package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.Performer;
import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.Role;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.PerformerService;
import com.company.ellRes.service.ReversService;
import com.company.ellRes.service.StaticService;
import com.company.ellRes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Iterator;

@Controller
@RequestMapping("/resolution")
public class resolutionController {

    @Autowired
    private PerformerService performerService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReversService reversService;

    @Autowired
    private StaticService staticService;


    @GetMapping("{resolution}")
    public String show(
            @PathVariable Resolution resolution,
            @AuthenticationPrincipal User user,
            Model model
    ){
        Iterable<Performer> performers = performerService.byResolution(resolution);
        Iterator<Performer> perfor = performers.iterator();
        if (resolution.getAgrees().getUsername().equals(user.getUsername()) || resolution.getFilling().getUsername().equals(user.getUsername()) || user.isAdmin()){
            String status = "";
            if (resolution.getStatus().getFinish()){
                model.addAttribute("resolution", resolution);
                model.addAttribute("statics", staticService.byVisa(resolution.getVisa()));
                model.addAttribute("revers", reversService.byResolution(resolution));
                model.addAttribute("revs", true);
                return "resolution/resolutionUser";
            }
            else if (resolution.getStatus().getRevers()){
                status = "Повернено до редагування";

                if (resolution.getFilling().getUsername().equals(user.getUsername())){
                    model.addAttribute("performers", performers);
                    model.addAttribute("revers", reversService.byResolutionActiv(resolution));
                    model.addAttribute("users",userService.allUser());
                    model.addAttribute("confirms",userService.userConfirms(Collections.singleton(Role.CONFIRM)));
                    return "resolution/resolutionEdit";
                }


            }else {
                if (resolution.getAgrees().getUsername().equals(user.getUsername())){
                    model.addAttribute("work", true);
                }
                status = "Візується";
            }
            model.addAttribute("status", status);
            model.addAttribute("resolution",resolution);
            model.addAttribute("performers", performers);
            return "resolution/resolution";
        }

        if(resolution.getStatus().getFinish()){
            while (perfor.hasNext()) {
                if (perfor.next().getUser() == user) {
                    model.addAttribute("resolution",resolution);
                    model.addAttribute("performers", performers);
                    return "resolution/resolution";
                }
            }
        }
        model.addAttribute("error", "Не достатньо прав");
        return "redirect:resolution/List";
    }
}
