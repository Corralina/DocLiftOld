package com.company.ellRes.controllers.actController.actAction;


import com.company.ellRes.domian.Act;
import com.company.ellRes.domian.StatusAct;
import com.company.ellRes.domian.TimingAct;
import com.company.ellRes.domian.User;
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

import java.util.ArrayList;

@Controller
@RequestMapping("actPause")
public class actPauseController {

    @Autowired
    private ActService actService;

    @Autowired
    private StatusActService statusActService;

    @Autowired
    private TimingActService timingActService;



    @GetMapping("{act}")
    public String pause(
            @AuthenticationPrincipal User user,
            @PathVariable Act act,
            Model model
    ){
        if(act.getAuthor().getUsername().equals(user.getUsername())){
            if (!act.getStatus().getDraft()){
                if (!act.getStatus().getFinish()){

                    StatusAct statusAct = act.getStatus();
                    statusAct.setDraft(true);
                    statusAct.setProcess(false);
                    statusActService.save(statusAct);

                }else{
                    model.addAttribute("error","Данний документ вже підписаний, тому не може бути поставлений на паузу");
                }
            }else{
                model.addAttribute("error","Данний документ вже поставлений на паузу");
            }
        }else {
            model.addAttribute("error","Відмовлено в доступі");
        }
        Iterable<TimingAct> timingActs = timingActService.list(user);
        ArrayList<Long> actsList = new ArrayList<Long>();
        for (TimingAct timingAct : timingActs){
            actsList.add(timingAct.getAct().getId());
        }

        Iterable<Act> acts = actService.listt(user, actsList);

        model.addAttribute("acts", acts);

        model.addAttribute("act", act);
        model.addAttribute("timings", timingActService.findByAct(act));
        return "act/act";
    }


}
