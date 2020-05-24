package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.demo.models.Statistic;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.StatisticRepository;

import java.time.LocalDateTime;

@Component
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticRepository statisticRepository;

    @Override
    public void saveStatistics(User user) {
        Statistic statistic = Statistic.builder()
                .createdAt(LocalDateTime.now())
                .role(user.getRole())
                .user(user)
                .build();
        statisticRepository.save(statistic);
    }
}
