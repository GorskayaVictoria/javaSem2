package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.ProfileForm;
import ru.itis.demo.dto.RegForm;
import ru.itis.demo.dto.SignUpDto;
import ru.itis.demo.security.UserDetailsImpl;
import ru.itis.demo.service.SignUpService;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("regForm", new RegForm());
        return "registration_page";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid RegForm form, BindingResult bindingResult, Model model) {
        model.addAttribute("regForm", form);
        if (!bindingResult.hasErrors()) {
            service.signUp(form);
            return "redirect:/start";
        }
        return "registration_page";
    }
}


