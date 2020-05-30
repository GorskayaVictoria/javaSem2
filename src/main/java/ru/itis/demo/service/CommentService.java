package ru.itis.demo.service;

import ru.itis.demo.dto.CommentAddDto;
import ru.itis.demo.dto.CommentDto;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.models.Comment;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;

import java.util.List;

public interface CommentService {
    void addComment(CommentAddDto form, User user);
    List<CommentDto> findByTransport(Transport trans);
}
