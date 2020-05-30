package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.OrderDto;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.Type;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.OrderRepository;
import ru.itis.demo.repositories.TransportsRepository;
import ru.itis.demo.repositories.UsersRepository;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransportsRepository transportsRepository;

    @Override
    public void regNewOrder(OrderDto form, Long trnsId, User user) {
        System.out.println(form);
        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .enable(true)
                .trans(transportsRepository.getOne(trnsId))
                .owner(user)
                .number(form.getNumber())
                .build();

        orderRepository.save(order);
    }
}
