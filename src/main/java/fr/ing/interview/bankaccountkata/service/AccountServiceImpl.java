package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService{

    public static final double AMOUNT_MIN = 0.01;
    public TransactionServiceImpl transactionService;



    @Override
    public double deposit(double amount, Account account) throws Exception{
        if(amount < AMOUNT_MIN){
            throw new Exception("invalide amount");
        }

        account.setBalance((account.getBalance()+amount));
        transactionService.addTransaction();

            return account.getBalance() ;

    }

    @Override
    public long withdraw() {
        return 0;
    }

    @Override
    public long getBalance() {
        return 0;
    }

    @Override
    public void addTransactionHistory() {

    }

    @Override
    public void getTransactionList() {

    }


}
