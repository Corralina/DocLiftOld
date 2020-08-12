package com.company.ellRes.controllers.userController;


import com.company.ellRes.domian.*;
import com.company.ellRes.errorConfig.errorController;
import com.company.ellRes.errorConfig.errorString;
import com.company.ellRes.errorConfig.errorValue;
import com.company.ellRes.service.ContactService;
import com.company.ellRes.service.IndividualService;
import com.company.ellRes.service.InformationService;
import com.company.ellRes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class registrationController {

    @Autowired
    private IndividualService individualService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;





    @GetMapping
    public String trageFirst(
            Model model
    ){
        return "user/registrationIndividual";
    }

    @PostMapping("individual")
    public String saveIndividual(
            @RequestParam Map<String,String> form,
            Individual individual,
            Model model
    ){
        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(individual.getSurname(), new int[] {1});
        if ( v != 0){
            model.addAttribute("error", "поле Surname" + errorValue.value(v));
            model.addAttribute("individual", individual);
            return "user/registrationIndividual";

        }

        v = errorController.parse(individual.getName(), new int[] {1});
        if ( v != 0){
            model.addAttribute("error", "поле Name" + errorValue.value(v));
            model.addAttribute("individual", individual);
            return "user/registrationIndividual";

        }

        v = errorController.parse(individual.getMiddlename(), new int[] {1});
        if ( v != 0){
            model.addAttribute("error", "поле Midlename" + errorValue.value(v));
            model.addAttribute("individual", individual);
            return "user/registrationIndividual";

        }

        v = errorController.parse(individual.getInitials(), new int[] {1});
        if ( v != 0){
            model.addAttribute("error", "поле Initials" + errorValue.value(v));
            model.addAttribute("individual", individual);
            return "user/registrationIndividual";

        }

        v = errorController.parse(individual.getPost(), new int[] {1});
        if ( v != 0){
            model.addAttribute("error", "поле Initials" + errorValue.value(v));
            model.addAttribute("individual", individual);
            return "user/registrationIndividual";

        }

        individualService.save(individual);

        model.addAttribute("individual", individual);

        return "user/registrationContact";
    }

    @PostMapping("contact")
    public String saveContact(
            @RequestParam Map<String,String> form,
            @RequestParam("individual") Individual individual,
            Contact contact,
            Model model
    ){
        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(contact.getMail(), new int[] {1,2,3});
        if(v != 0){
            model.addAttribute("error", "Поле mail " + errorValue.value(v));
            model.addAttribute("individual", individual);
            model.addAttribute("contact", contact);

            return "user/registrationContact";
        }

        v = errorController.parse(contact.getPhone(), new int[] {1,2,4});
        if(v != 0){
            model.addAttribute("error", "Поле phone " + errorValue.value(v));
            model.addAttribute("individual", individual);
            model.addAttribute("contact", contact);

            return "user/registrationContact";
        }


        contactService.save(contact);

        Information information = new Information();
        information.setIndividual(individual);
        information.setContact(contact);
        informationService.save(information);

        model.addAttribute("information", information);

        return "user/registration";
    }

    @PostMapping("registration")
    public String saveUser(
            @RequestParam Map<String,String> form,
            User user,
            Model model
    ){

        if(userService.loadUserByUsername(user.getUsername()) != null){
            model.addAttribute("error", "Користувач із логіном " + user.getUsername() + " уже існеє");
            model.addAttribute("user",user);
            return "user/registration";
        }

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(user.getUsername(), new int[] {1,2});
        if(v != 0){
            model.addAttribute("error", "Поле логін " + errorValue.value(v));
            return "user/registrationContact";
        }

        v = errorController.parse(user.getPassword(), new int[] {1});
        if(v != 0){
            model.addAttribute("error", "Поле пароль " + errorValue.value(v));
            return "user/registrationContact";
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.userSave(user);


        return "menu";
    }


}
