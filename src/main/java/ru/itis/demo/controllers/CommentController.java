package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.CommentAddDto;
import ru.itis.demo.dto.CommentDto;
import ru.itis.demo.repositories.CommentRepository;
import ru.itis.demo.repositories.TransportsRepository;
import ru.itis.demo.security.UserDetailsImpl;
import ru.itis.demo.service.CommentService;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TransportsRepository transportsRepository;

    @PostMapping(value = "/addComment/{transport_id}")
    public String addComment(CommentAddDto commentDto, Authentication authentication,@PathVariable("transport_id") Long transId) {
        if(authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            commentDto.setTransport(transportsRepository.getOne(transId));
            commentService.addComment(commentDto,userDetails.getUser());
            return "redirect:/transports";
        } else return "redirect:/signIn";
    }
}