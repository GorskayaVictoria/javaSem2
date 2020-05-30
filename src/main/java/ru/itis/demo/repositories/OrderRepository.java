package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Statistic;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
