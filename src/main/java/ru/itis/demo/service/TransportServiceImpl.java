package ru.itis.demo.service;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.dto.TransportResult;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.dto.UsersSearchResult;
import ru.itis.demo.models.*;
import ru.itis.demo.repositories.FileInfoRepository;
import ru.itis.demo.repositories.TransportsRepository;
import ru.itis.demo.util.FileStorageUtil;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ru.itis.demo.dto.TransportDto.from;

@Service
public class TransportServiceImpl implements TransportService{


    @Autowired
    private TransportsRepository transportsRepository;



    @Override
    public List<TransportDto> getTransports() {
        return from(transportsRepository.findAll());
    }

    @Override
    public TransportResult getTransports(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 3);
        Page<Transport> pageResult = transportsRepository.findAll(pageRequest);
        return TransportResult.builder()
                .transports(from(pageResult.getContent()))
                .count(pageResult.getTotalPages())
                .build();
    }



    @Override
    public TransportDto getConcreteTransport(Long transportId) {
        Transport transport = transportsRepository.getOne(transportId);
        return from(transport);
    }

    @Override
    public List<TransportDto> search(String name) {
        return from(transportsRepository.findAllByNameContainsIgnoreCase(name));
    }

    @Override
    public void regNewTrans(TransportDto form) {
        System.out.println(form);
        Transport transport = Transport.builder()
                .city(form.getCity())
                .name(form.getName())
                .info(form.getInfo())
                .createdAt(LocalDateTime.now())
                .year(form.getYear())
                .filePath(form.getFilePath())
                .type(Type.CAR)
                .build();

        transportsRepository.save(transport);
    }

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        // вытягиваем всю инофрмацию о файле для сохранения ее в бд
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        System.out.println("ertyui");
        System.out.println(fileInfo);

        // сохраняем информацию о файле

        System.out.println("efwefwe");
        fileInfoRepository.save(fileInfo);
        // переносим файл на наш диск

        System.out.println("ertwetwtyui");

        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());

        System.out.println(fileInfo.getStorageFileName());
        // возвращаем имя файла - новое
        return fileInfo.getStorageFileName();
    }

    @Override
    public List<Transport> searchTrans(String name) {
        return transportsRepository.searchTrans(name);
    }


    @SneakyThrows
    public void convertPdfToJpg(Transport transport, String newFileName) {
        System.out.println(newFileName);
        System.out.println(transport.getSourceFile());
        PDDocument pdf = PDDocument.load(transport.getSourceFile());
        PDFRenderer renderer = new PDFRenderer(pdf);
        BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIOUtil.writeImage(image, newFileName, 300);
        pdf.close();
        File resultFile = new File(newFileName);
        System.out.println(resultFile);

        transport.setSourceFile(resultFile);
        transport.setFilePath(newFileName.split("\\/")[10]);
        System.out.println("hii");
    }

    @Transactional
    @Override
    public void convert() {
        List<Transport> transports = transportsRepository.findAll();


        for (Transport tran : transports) {
            String newFileName = "/Users/admin/IdeaProjects/demo/src/main/resources/static/img/"+  UUID.randomUUID().toString() + ".jpg";
            System.out.println(tran.getFilePath());
            System.out.println((tran.getFilePath().split("\\.")[1]));
            if (tran.getFilePath().split("\\.")[1].equals("pdf")) {
                convertPdfToJpg(tran, newFileName);
                System.out.println("fghj");
                System.out.println(tran.getFilePath());
                transportsRepository.save(tran);
            }
        }
    }





}
