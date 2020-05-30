package ru.itis.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.dto.TransportResult;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;
import ru.itis.demo.service.TransportService;
import ru.itis.demo.service.UsersService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class UsersController {


    @Autowired
    private UsersService usersService;

    @Autowired
    private TransportService transportService;


    @GetMapping("/user/{user-id}")
    public String getConcreteUserPage(@PathVariable("user-id") Long userId, Model model) {
        UserDto user = usersService.getConcreteUser(userId);
        model.addAttribute("user", user);
        return "otherProfile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{user-id}/delete")
    public String deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return "redirect:/users";
    }


    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<UserDto> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/searchUsers")
    @ResponseBody
    public List<User> searchUsers(@RequestParam("name") String name) {
        return usersService.searchUsers(name);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{user-id}/transports")
    public String getMyTransports(Model model,@PathVariable("user-id") Long userId) {
        System.out.println(userId);
        UserDto user = usersService.getConcreteUser(userId);
        System.out.println("hjkhjkhk");
        System.out.println(user);
        System.out.println("jhjhk");
        List<Transport> transports = user.getTransports();
        model.addAttribute("transports", transports);
        return "trans2";

    }


}
