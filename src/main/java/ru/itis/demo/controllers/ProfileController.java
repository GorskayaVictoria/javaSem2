package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.ProfileForm;

import ru.itis.demo.security.UserDetailsImpl;
import ru.itis.demo.service.UsersService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {



    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("profileForm", new ProfileForm());
        return "otherProfile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication authentication, @Valid ProfileForm form, BindingResult bindingResult, Model model) {
        System.out.println(form);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        System.out.println(bindingResult.getAllErrors());
        model.addAttribute("profileForm", form);
        return "otherProfile";
    }

}
