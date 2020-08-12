package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.Document;
import com.company.ellRes.domian.Individual;
import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import com.company.ellRes.fileService.createFileResolution;
import com.company.ellRes.service.ReversService;
import com.company.ellRes.service.StaticService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/resolutionForm")
public class resolutionFormController {

    @Autowired
    private StaticService staticService;

    @Autowired
    private ReversService reversService;

    @PostMapping
    public String resolv(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            @RequestParam("resolution") Resolution resolution,
            Model model
    ) throws IOException, DocumentException {
        createFileResolution createFileResolution = new createFileResolution();
        Boolean caption  = false;
        if (resolution.getAgrees().getInformation().getIndividual().getCaption() != null && form.containsKey("cap")){
            caption = true;
        }
        String href = createFileResolution.formRes(resolution, staticService.byVisa(resolution.getVisa()), caption);

        model.addAttribute("resolution", resolution);
        model.addAttribute("statics", staticService.byVisa(resolution.getVisa()));
        model.addAttribute("resolutionFile", href);

        if (user.isAdmin() || resolution.getFilling().getUsername().equals(user.getUsername()) || resolution.getAgrees().getUsername().equals(user.getUsername())){
            model.addAttribute("revers", reversService.byResolution(resolution));
            model.addAttribute("revs", true);
        }
        return "resolution/resolutionUser";
    }
}
