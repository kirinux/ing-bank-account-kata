package fr.ing.interview.service;

import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.dto.TransferResponseDto;
import fr.ing.interview.entity.Account;
import fr.ing.interview.persistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private static final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(0.01);

    private static final String ERROR_DEPOSIT_TOO_LOW = "Deposit is too low";

    private static final String ERROR_INEXISTANT_ACCOUNT = "Account does not exist";

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public TransferResponseDto deposit(TransferRequestDto transferRequestDto) {
        TransferResponseDto response;

        if (MIN_AMOUNT.compareTo(transferRequestDto.getAmount()) < 0) {
            Optional<Account> accountOptional = accountRepository.findById(transferRequestDto.getAccountId());

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                account.setBalance(account.getBalance().add(transferRequestDto.getAmount()));
                accountRepository.save(account);

                response = TransferResponseDto.builder().success(true).build();
            } else {
                response = TransferResponseDto.builder().success(false).message(ERROR_INEXISTANT_ACCOUNT).build();
            }
        } else {
            response = TransferResponseDto.builder().success(false).message(ERROR_DEPOSIT_TOO_LOW).build();
        }

        return response;
    }

}
