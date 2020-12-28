package com.company.ellRes.controllers.actController;


import com.company.ellRes.domian.User;
import com.company.ellRes.service.DocumentActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("actList")
public class actListController {

    @Autowired
    private DocumentActService documentActService;

    @GetMapping
    public String list(@AuthenticationPrincipal User user, Model model){

        model.addAttribute("documents", documentActService.listActFalse(user));

        return "act/actList";

    }
}
