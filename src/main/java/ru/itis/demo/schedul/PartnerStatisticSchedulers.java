package ru.itis.demo.schedul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.demo.models.Role;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UsersRepository;
import ru.itis.demo.service.StatisticService;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Configuration
@EnableScheduling
@Slf4j
public class PartnerStatisticSchedulers {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @Scheduled(cron = "0 10 * * * ?")
    public void run() {
        System.out.println("hiii");
        List<User> partners = usersRepository.findAllByRole(Role.USER);
        System.out.println("hiii");

        for (User partner : partners) {
            System.out.println("hiii");
            System.out.println(partner.getName());
            statisticService.saveStatistics(partner);
        }
    }
}
