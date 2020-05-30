package ru.itis.demo.models;

import com.sun.org.glassfish.external.statistics.Statistic;
import lombok.*;
import org.hibernate.annotations.Where;
import sun.plugin2.message.Serializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = { "transportList" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itis_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
//    private Integer age;
    private String hashPassword;

    private LocalDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String confirmCode;

//    @OneToMany(mappedBy = "user")
//    private List<Statistic> statisticList;

    @OneToMany(mappedBy = "owner")
    @Where(clause = "enable = 'true'")
    private List<Transport> transportList;
}
