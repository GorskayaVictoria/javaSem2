package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.CommentAddDto;
import ru.itis.demo.dto.CommentDto;
import ru.itis.demo.dto.TransportDto;
import ru.itis.demo.models.Comment;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.CommentRepository;
import ru.itis.demo.repositories.TransportsRepository;
import ru.itis.demo.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.itis.demo.dto.CommentDto.from;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentsRepository;

    @Autowired
    private TransportsRepository transportsRepository;

    @Override
    public List<CommentDto> findByTransport(Transport trans){
        return from(commentsRepository.findByTransport(trans));
    }

    @Override
    public void addComment(CommentAddDto form, User user ){
        System.out.println(form);
        Comment comment = Comment.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .transport(form.getTransport())
                .text(form.getText())
                .build();
        commentsRepository.save(comment);
    }
}
