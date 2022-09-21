package fr.ing.interview.bankaccountkata.exception;

public class AccountNotFoundException extends RuntimeException {

    private String accountNumber;

    public AccountNotFoundException(String accountNumber) {
        super("Le compte avec l'IBAN [ " + accountNumber + " ] est introuvable ");
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
