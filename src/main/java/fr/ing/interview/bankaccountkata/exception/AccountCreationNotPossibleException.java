package fr.ing.interview.bankaccountkata.exception;

public class AccountCreationNotPossibleException extends RuntimeException {

    private String accountNumber;


    public AccountCreationNotPossibleException(String accountNumber) {
        super("Le numéro de compte [ " + accountNumber + " ] existe déjà !");
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
