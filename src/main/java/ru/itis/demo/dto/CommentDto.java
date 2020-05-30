package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.*;


import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private TransportDto transport;
    private UserDto user;
    private String text;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .user(UserDto.from(comment.getUser()))
                .transport(TransportDto.from(comment.getTransport()))
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }


}
