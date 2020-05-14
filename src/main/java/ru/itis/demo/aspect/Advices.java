package ru.itis.demo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

//дата/время/класс, название метода, формальные параметры метода (toString представление каждого аргумента)

@Component
@Aspect
@Slf4j
public class Advices {

    @Before(value = "execution(* ru.itis.demo.service.*.*(..))")
    public void requestLogging(JoinPoint jp) {
        String[] packages = jp.getSignature().getDeclaringTypeName().split("\\.");
//        for (String p:packages) {
//            System.out.println(p);
//        }
        String classOfMethod = packages[packages.length - 1];
//        System.out.println(LocalDateTime.now().toString());
        String[] times = LocalDateTime.now().toString().split("T");
        String date = times[0];
        String time = times[1];
        String params = (jp.getArgs().length == 0 ? "no params" : Arrays.toString(jp.getArgs()));
        log.info("\n request method: " + jp.getSignature().getName() + " of " + classOfMethod +
                " class. \n Request date: " + date + ". \n Request time: " + time + ". \n Method params: "
                + params);
    }
}
