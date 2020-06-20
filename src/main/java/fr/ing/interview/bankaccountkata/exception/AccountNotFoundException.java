package fr.ing.interview.bankaccountkata.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String s) {
        super(s);
    }
}
