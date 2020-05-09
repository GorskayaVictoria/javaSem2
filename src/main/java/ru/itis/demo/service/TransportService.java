package ru.itis.demo.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.dto.TransportResult;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;

import java.io.IOException;
import java.util.List;

public interface TransportService {
    List<TransportDto> getTransports();
    TransportResult getTransports(Integer page);

    TransportDto getConcreteTransport(Long transportId);

    List<TransportDto> search(String name);

    void regNewTrans(TransportDto form);
    // сохраняет файл на сервере
    String saveFile(MultipartFile file) throws IOException;
    List<Transport> searchTrans(String name);



}
