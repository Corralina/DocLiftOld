package com.company.ellRes.controllers.actController.actAction;


import com.company.ellRes.domian.*;
import com.company.ellRes.service.ActService;
import com.company.ellRes.service.StatusActService;
import com.company.ellRes.service.TimingActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;

@Controller
@RequestMapping("actConfirm")
public class actConfirmUserController {

    @Autowired
    private com.company.ellRes.dataService.dataToday dataToday;

    @Autowired
    private ActService actService;

    @Autowired
    private StatusActService statusActService;

    @Autowired
    private TimingActService timingActService;



    @GetMapping("{act}")
    public String confirm(
            @AuthenticationPrincipal User user,
            @PathVariable Act act,
            @RequestParam("timing") TimingAct timingAct,
            Model model
    ) throws ParseException {
        if(timingAct.getUser().getUsername().equals(user.getUsername())){
            if (!act.getStatus().getDraft()){
                if (!act.getStatus().getFinish()){
                    timingAct.setSing(true);
                    timingAct.setDate(dataToday.dateToday());


                }else{
                    model.addAttribute("error","Данний документ вже підписаний");
                }
            }else{
                model.addAttribute("error","Данний документ поставлений на паузу");
            }
        }else {
            model.addAttribute("error","Відмовлено в доступі");
        }
        Iterable<TimingAct> timingActs = timingActService.list(user);
        ArrayList<Long> actsList = new ArrayList<Long>();
        for (TimingAct timingA : timingActs){
            actsList.add(timingA.getAct().getId());
        }

        Iterable<Act> acts = actService.listt(user, actsList);

        model.addAttribute("acts", acts);

        model.addAttribute("act", act);
        model.addAttribute("timings", timingActService.findByAct(act));
        return "act/act";
    }


}
