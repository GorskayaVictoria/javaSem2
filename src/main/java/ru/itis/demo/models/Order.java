package ru.itis.demo.models;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itis_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;


    private Boolean enable;

    private Long number;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Transport trans;


}
