package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.models.Statistic;
import ru.itis.demo.models.User;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

}
