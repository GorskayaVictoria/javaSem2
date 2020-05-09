package ru.itis.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.service.FileStorageService;
import ru.itis.demo.service.TransportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StorageController {

    @Autowired
    private TransportService service;

    @Autowired
    private FileStorageService fileStorageService;


    // страница, которая позволяет загружать файлы

    // принимает файлы
    // MultipartFile - файл, который вы принимаете
    // ResponseEntity - класс, который позволяет отправить в ответе
    // не только тело, но и статус и заголовки ответа
//    @PostMapping("/files")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        // сохраняем файл на диск
//        System.out.println("hihihih");
//        String filePath = service.saveFile(file);
//        System.out.println(filePath);
//        // отправляем пользователю полный путь к этому файлу
//        return ResponseEntity
//                .ok()
//                .body(filePath);
//    }



    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // сохраняем файл на диск
        System.out.println("her");
        String filePath = service.saveFile(file);
        // отправляем пользователю полный путь к этому файлу
        return ResponseEntity
                .ok()
                .body(filePath);
    }
//    @GetMapping("/files123")
//    public void getFile(@PathVariable("file-name") String fileName,
//                        HttpServletResponse response) {
//        System.out.println("asdfghj");
//        fileStorageService.writeFileToResponse(fileName, response);
//    }




}

