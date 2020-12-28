package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.TimingAct;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.ActService;
import com.company.ellRes.service.TimingActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("actRegisterList")
public class actRegisterList {

    @Autowired
    private TimingActService timingActService;

    @Autowired
    private ActService actService;



    @GetMapping
    public String list(
            @AuthenticationPrincipal User user,
            Model model
    ){

        Iterable<TimingAct> timingActs = timingActService.list(user);
        ArrayList<Long> actsList = new ArrayList<Long>();
        for (TimingAct timingAct : timingActs){
            actsList.add(timingAct.getAct().getId());
        }

        Iterable<Act> acts = actService.listt(user, actsList);

        model.addAttribute("acts", acts);
        return "act/actRegisterList";
    }

    @GetMapping("unfinished")
    public String UnList(
            @AuthenticationPrincipal User user,
            Model model
    ){
        return "act/actRegisterList";
    }


}
