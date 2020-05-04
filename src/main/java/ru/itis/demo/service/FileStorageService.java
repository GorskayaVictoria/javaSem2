package ru.itis.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileStorageService {

    // сохраняет файл на сервере
    String saveFile(MultipartFile file) throws IOException;
    // отправляет файл по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);

}
