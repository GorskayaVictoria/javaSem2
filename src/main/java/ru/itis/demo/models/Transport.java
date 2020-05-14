package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itis_trans")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String info;
    private Integer year;
    private String filePath;


    private LocalDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private Boolean enable;

    @ManyToOne
    private User owner;

    @Transient
    private File sourceFile;

    @Transient
    private String fileName;



    @PostLoad
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)

        sourceFile = new File("/Users/admin/IdeaProjects/demo/src/main/resources/static/img/",filePath);
        fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("load " + fileName);
    }

    @PreUpdate
    public void updateFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        this.filePath = sourceFile.getPath().split("\\/")[10];
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("upd " + fileName);
    }

}