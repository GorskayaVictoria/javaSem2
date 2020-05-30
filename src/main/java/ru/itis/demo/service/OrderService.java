package ru.itis.demo.service;

import ru.itis.demo.dto.OrderDto;
import ru.itis.demo.dto.RegForm;
import ru.itis.demo.models.User;

public interface OrderService {
    void regNewOrder(OrderDto form, Long transId, User user);
}
