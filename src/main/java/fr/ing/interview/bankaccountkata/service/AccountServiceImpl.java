package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    public static final double AMOUNT_MIN = 0.01;
    private TransactionServiceImpl transactionService;



    @Override
    public Account deposit(double amount, Account account) throws Exception{
        if(account == null){
            throw new RuntimeException ("account is null");
        }

        if(amount < AMOUNT_MIN){
            throw new Exception();
        }

        account.setBalance((account.getBalance()+amount));
        transactionService.addTransaction(new Transaction(1L, LocalDate.now(), amount));
        return account ;
    }

    @Override
    public Account withdraw(double amount, Account account) throws Exception {

        if(account == null){
            throw new RuntimeException ("account is null");
        }

        if((account.getBalance() - amount) < 0 ){
            throw new RuntimeException ("you don't have enough money");
        }


        account.setBalance((account.getBalance() - amount));
        transactionService.addTransaction(new Transaction(1L, LocalDate.now(), amount));

        return account ;
    }





}
