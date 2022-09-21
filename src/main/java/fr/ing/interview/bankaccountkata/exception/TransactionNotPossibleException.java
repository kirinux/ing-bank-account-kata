package fr.ing.interview.bankaccountkata.exception;

public class TransactionNotPossibleException extends RuntimeException {

    private String accountNumber;


    public TransactionNotPossibleException(String accountNumber) {
        super("Transaction pour le compte [ " + accountNumber + " ] non possible !");
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
