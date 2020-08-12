package com.company.ellRes.controllers.userController;


import com.company.ellRes.domian.Send;
import com.company.ellRes.domian.User;
import com.company.ellRes.service.SendService;
import com.company.ellRes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/userSending")
public class userSendingController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendService sendService;


    @GetMapping
    public String us(
            Model model
    ){
        model.addAttribute("users", userService.allUser());
        Iterable<Send> usersSend = sendService.who();
        usersSend.forEach(userSend ->{
            model.addAttribute("userSend", userSend);
        });


        return "user/userSending";

    }

    @PostMapping
    public String save(
            @RequestParam Map<String,String> form,
            @RequestParam("send") Send send,
            @RequestParam("userSend") User user
    ){
        send.setUser(user);
        sendService.save(send);

        return "redirect:/userSending";
    }
}
