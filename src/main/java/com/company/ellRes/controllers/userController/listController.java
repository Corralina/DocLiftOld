package com.company.ellRes.controllers.userController;


import com.company.ellRes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userList")
public class listController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(
            Model model
    ){
        model.addAttribute("users", userService.allUser());
        return "user/userList";
    }
}
