package fr.ing.interview.service;

import fr.ing.interview.exception.InvalidDepositException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    public void deposit(long customerId, long accountId, BigDecimal amount) throws InvalidDepositException {

    }

}
