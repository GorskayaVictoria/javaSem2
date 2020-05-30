package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.itis.demo.dto.CommentDto;
import ru.itis.demo.models.Comment;
import ru.itis.demo.models.Transport;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment,Long> {
    List<Comment> findByTransport(Transport transport);

}
