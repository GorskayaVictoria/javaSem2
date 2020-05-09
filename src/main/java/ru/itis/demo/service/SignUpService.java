package ru.itis.demo.service;

import ru.itis.demo.dto.RegForm;
import ru.itis.demo.dto.SignUpDto;

public interface SignUpService {
    void signUp(RegForm form);
}
