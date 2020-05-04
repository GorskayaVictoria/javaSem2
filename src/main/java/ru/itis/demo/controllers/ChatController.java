package ru.itis.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.demo.models.User;
import ru.itis.demo.security.UserDetailsImpl;

import java.util.UUID;

@Controller
public class ChatController {
    @GetMapping("/chat")
    public String getChat(Authentication authentication, Model model){
        System.out.println(authentication);

        model.addAttribute("pageId", UUID.randomUUID().toString());
        if (authentication!= null){
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("pageName", user.getName());
        }else{
            model.addAttribute("pageName", "Anon");
        }

        return "chat";
    }
}
