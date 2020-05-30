package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.Type;
import ru.itis.demo.models.User;


import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long number;
    private Transport transport;
    private Boolean enable;
    private User owner;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .enable(order.getEnable())
                .number(order.getNumber())
                .owner(order.getOwner())
                .transport(order.getTrans())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }


}
