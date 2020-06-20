package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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

        Transaction t  = new Transaction(2L, LocalDate.now(), amount,account);
        account.getTransactions().add(t);
      //  transactionService.addTransaction( t ,account);

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
        Transaction t  = new Transaction(1L, LocalDate.now(), amount,account);
        account.getTransactions().add(t);

       // transactionService.addTransaction( t ,account);

        return account ;
    }



    @Override
    public double getBalance(int idAccount) {
        return getAccount(idAccount).getBalance() ;
    }



    @Override
    public void getTransactionList() {

    }
    Account getAccount(int idAccount){ return null;}
    List<Account> getListAccounts(){ return null;}

}
