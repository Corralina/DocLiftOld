package com.company.ellRes.controllers.userController;


import com.company.ellRes.domian.Contact;
import com.company.ellRes.domian.Individual;
import com.company.ellRes.domian.Role;
import com.company.ellRes.domian.User;
import com.company.ellRes.errorConfig.errorController;
import com.company.ellRes.errorConfig.errorValue;
import com.company.ellRes.fileService.SaveCaption;
import com.company.ellRes.service.ContactService;
import com.company.ellRes.service.IndividualService;
import com.company.ellRes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userEdit")
public class editController {

    @Autowired
    UserService userService;

    @Autowired
    IndividualService individualService;

    @Autowired
    ContactService contactService;

    @Autowired
    SaveCaption saveCaption;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @GetMapping("{user}")
    public String editUser(
            @PathVariable User user,
            Map<String, Object> model
    ){
        model.put("user",user);
        model.put("roles", Role.values());

        return "user/userEdit";
    }


    @PostMapping
    public String saveEdit(
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user,
            @RequestParam("caption") MultipartFile caption,
            Model model
    ) throws IOException {

        errorController errorController = new errorController();
        errorValue errorValue = new errorValue();

        int v = errorController.parse(form.get("surname"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле прізвище " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("name"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле ім'я " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("middlename"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле побатькові " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("initials"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле ініціали " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("post"), new int[] {1});
        if (v != 0){
            model.addAttribute("error", "Поле посада " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("mail"), new int[] {1,2,3});
        if (v != 0){
            model.addAttribute("error", "Поле пошта " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("phone"), new int[] {1,2,4});
        if (v != 0){
            model.addAttribute("error", "Поле телефон " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }

        v = errorController.parse(form.get("username"), new int[] {1,2});
        if (v != 0){
            model.addAttribute("error", "Поле логін " + errorValue.value(v));
            model.addAttribute("user",user);
            model.addAttribute("roles", Role.values());
            return "user/userEdit";
        }


        if (!form.get("password").equals("")){
            v = errorController.parse(form.get("password"), new int[] {1});
            if (v != 0){
                model.addAttribute("error", "Поле пароль " + errorValue.value(v));
                model.addAttribute("user",user);
                model.addAttribute("roles", Role.values());
                return "user/userEdit";
            }
        }






        Individual individual = user.getInformation().getIndividual();
        individual.setSurname(form.get("surname"));
        individual.setName(form.get("name"));
        individual.setMiddlename(form.get("middlename"));
        individual.setInitials(form.get("initials"));
        individual.setPost(form.get("post"));
        if(!caption.getOriginalFilename().equals("")){
            individual.setCaption(saveCaption.uploadImg(caption));
        }
        individualService.save(individual);

        Contact contact = user.getInformation().getContact();
        contact.setPhone(form.get("phone"));
        contact.setMail(form.get("mail"));
        contactService.save(contact);

        user.setUsername(form.get("username"));
        if (!form.get("password").equals("")){
            user.setPassword(passwordEncoder.encode(form.get("password")));
        }

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains((key))){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.userSave(user);

        return "redirect:userList";
    }


}
