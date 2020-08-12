package com.company.ellRes.controllers.resolutionControler;


import com.company.ellRes.domian.Resolution;
import com.company.ellRes.domian.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Controller
@RequestMapping("/resolutionPrint")
public class ResolutionPrintController {

    @Value("${upload.path.document}")
    private String uploadPathDocument;



    @GetMapping("{resolution}")
    public String print(
            @PathVariable Resolution resolution,
            @AuthenticationPrincipal User user,
            Model model
    ) throws FileNotFoundException {

        DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
        PrintService[] services =
                PrintServiceLookup.lookupPrintServices(flavor, null);
        InputStream inputStream = new FileInputStream(uploadPathDocument + "\\" + resolution.getDocument().getName());
        Doc doc = new SimpleDoc(inputStream, flavor, null);
        if (services.length > 0) {
            DocPrintJob job = services[0].createPrintJob();
            try {
                job.print(doc, null);
            } catch (PrintException pe) {}
        }

        return "menu";

    }
}
