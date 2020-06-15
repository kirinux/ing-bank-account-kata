package fr.ing.bank.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
