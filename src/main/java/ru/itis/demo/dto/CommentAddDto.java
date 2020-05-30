package ru.itis.demo.dto;

import lombok.Data;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;

@Data
public class CommentAddDto {
    String text;
    User user;
    Transport transport;
}
