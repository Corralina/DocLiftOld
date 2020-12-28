package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.Act;
import com.company.ellRes.service.TimingActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("act")
public class actShowController {

    @Autowired
    private TimingActService timingActService;


    @GetMapping("{act}")
    public String act(
            @PathVariable Act act,
            Model model
    ){
        model.addAttribute("act", act);
        model.addAttribute("timings", timingActService.findByAct(act));


        return "act/act";

    }
}
