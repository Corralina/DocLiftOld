package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.ReversService;
import com.company.ellRes.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resolutionShow")
public class resolutionUserController {

    @Autowired
    private StaticService staticService;

    @Autowired
    private ReversService reversService;


    @GetMapping("{resolution}")
    public String show(
            @PathVariable Resolution resolution,
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("resolution", resolution);
        model.addAttribute("statics", staticService.byVisa(resolution.getVisa()));

        if (user.isAdmin() || resolution.getFilling().getUsername().equals(user.getUsername()) || resolution.getAgrees().getUsername().equals(user.getUsername())){
            model.addAttribute("revers", reversService.byResolution(resolution));
            model.addAttribute("revs", true);
        }
        return "resolution/resolutionUser";

    }
}
