package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.*;
import com.company.ellRes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("actRegister")
public class ActRegisterController {

    @Autowired
    private com.company.ellRes.dataService.dataToday dataToday;

    @Autowired
    private UserService userService;

    @Autowired
    private ActService actService;

    @Autowired
    private StatusActService statusActService;

    @Autowired
    private TimingActService timingActService;

    @Autowired
    private DocumentActService documentActService;

    @Autowired
    private AgreesActService agreesActService;


    @GetMapping("{document}")
    public String userEditForm(
            @AuthenticationPrincipal User user,
            @PathVariable DocumentAct document,
            Model model
    ){
        if (!document.isAct()) {
            model.addAttribute("documentAct", document);
            model.addAttribute("users", userService.allUser());
            model.addAttribute("confirms", userService.userConfirms(Collections.singleton(Role.CONFIRM)));
            return "act/actSingCreate";
        }
        model.addAttribute("error","На даний документ вже накладено підписи");
        model.addAttribute("documents", documentActService.listActFalse(user));
        return "act/actList";



    }

    @PostMapping
    public String saveRes(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Act act,
            Model model
    ) throws ParseException {

        DocumentAct doc = act.getDocumentAct();
        if (!doc.isAct()) {

            StatusAct statusAct = new StatusAct();
            statusAct.setFinish(false);
            statusAct.setRevers(false);
            statusAct.setDraft(false);
            statusAct.setProcess(true);

            AgreesAct agreesAct = new AgreesAct();
            agreesAct.setUser(userService.findByUsername(form.get("agrees")));
            agreesAct.setPost(form.get("postAgrees"));
            agreesAct.setSing(false);



            act.setStatus(statusActService.save(statusAct));
            act.setAuthor(user);
            act.setDate(dataToday.dateToday());
            act.setTime(dataToday.timeToday());
            act.setAgrees(agreesActService.save(agreesAct));
            actService.save(act);

            doc.setAct(true);
            documentActService.save(doc);

            int i = 1;
            for(String key : form.keySet()){
                if (key.indexOf("perfo")>=0){
                    TimingAct timingAct = new TimingAct();
                    timingAct.setAct(act);
                    timingAct.setUser(userService.findByUsername(form.get(key)));
                    timingAct.setPost(form.get(key.replace("performs", "note")));
                    timingAct.setStatus(i);
                    timingActService.save(timingAct);
                    i++;
                }
            }

            return "redirect:actList";
        }

        model.addAttribute("error","На даний документ вже накладено підписи");
        return "redirect:actList";
    }
}
