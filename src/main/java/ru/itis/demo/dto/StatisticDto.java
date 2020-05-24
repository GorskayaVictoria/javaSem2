package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.Role;
import ru.itis.demo.models.Statistic;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticDto {
    private Long id;
    private Role role;
    private User user;


    public static StatisticDto from(Statistic statistic) {
        return StatisticDto.builder()
                .id(statistic.getId())
                .role(statistic.getRole())
                .user(statistic.getUser())
                .build();
    }

    public static List<StatisticDto> from(List<Statistic> statistics) {
        return statistics.stream()
                .map(StatisticDto::from)
                .collect(Collectors.toList());
    }




}
