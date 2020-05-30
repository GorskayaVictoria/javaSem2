package ru.itis.demo.controllers;


import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.CommentDto;
import ru.itis.demo.dto.OrderDto;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.dto.TransportResult;
import ru.itis.demo.models.Comment;
import ru.itis.demo.models.Transport;
import ru.itis.demo.repositories.TransportsRepository;
import ru.itis.demo.security.UserDetailsImpl;
import ru.itis.demo.service.CommentService;
import ru.itis.demo.service.OrderService;
import ru.itis.demo.service.TransportService;

import java.io.IOException;
import java.util.List;



@Controller
public class TransportController {



    @Autowired
    private TransportService transportService;

    @Autowired
    private TransportsRepository transportsRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/transport/{transport-id}")
    public String getConcreteTransport(@PathVariable("transport-id") Long transportId, Model model) {

        TransportDto transport = transportService.getConcreteTransport(transportId);
        model.addAttribute("transport", transport);
        List<CommentDto> comments = commentService.findByTransport(transportsRepository.getOne(transportId));
        model.addAttribute("messages",comments);
        return "transport_page";
    }



    @GetMapping("/transports")
    public String getTransports(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page) {
            model.addAttribute("page", page);
            TransportResult  transports = transportService.getTransports(page);
        System.out.println(transports);
            model.addAttribute("transports", transports.getTransports());
            return "trans";

    }

    @GetMapping("/hi")
    public ResponseEntity<?> hi() {
        transportService.convert();
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/newTrans")
    public String getNewTrans() {
        System.out.println("qwerty");
        return "regTrans_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/newTrans")
    public String handleFileUpload(TransportDto form) throws IOException {
        // сохраняем файл на диск
        transportService.regNewTrans(form);
        // отправляем пользователю полный путь к этому файлу
        return "redirect:/transports";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/order/{transport-id}")
    public String MakeOrder(Authentication authentication, OrderDto form, @PathVariable("transport-id") Long transportId) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        orderService.regNewOrder(form,transportId, userDetails.getUser());
        return "redirect:/transports";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order/{transport-id}")
    public String MakeOrder(Authentication authentication, @PathVariable("transport-id") Long transportId) throws IOException {
        return "newOrder";
    }


    @GetMapping("/transportsTest")
    public String getTransPage(Model model) {
        List<TransportDto> transports = transportService.getTransports();
        model.addAttribute("trans", transports);
        return "search";

    }


    @GetMapping("/searchTrans")
    @ResponseBody
    public List<Transport> searchUsers(@RequestParam("name") String name) {
        return transportService.searchTrans(name);
    }
}



