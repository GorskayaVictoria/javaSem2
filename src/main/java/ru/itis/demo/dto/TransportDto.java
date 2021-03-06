package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.Transport;
import ru.itis.demo.models.Type;
import ru.itis.demo.models.User;


import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportDto {
    private Long id;
    private String name;
    private String city;
    private String info;
    private Integer year;
    private Type type;
    private String filePath;
    private Boolean enable;
    private User owner;

    public static TransportDto from(Transport transport) {
        return TransportDto.builder()
                .id(transport.getId())
                .name(transport.getName())
                .info(transport.getInfo())
                .year(transport.getYear())
                .type(transport.getType())
                .city(transport.getCity())
                .enable(transport.getEnable())
                .owner(transport.getOwner())
                .filePath(transport.getFilePath())
                .build();
    }

    public static List<TransportDto> from(List<Transport> transports) {
        return transports.stream()
                .map(TransportDto::from)
                .collect(Collectors.toList());
    }


}
