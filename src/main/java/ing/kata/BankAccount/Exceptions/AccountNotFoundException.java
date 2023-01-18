package ing.kata.BankAccount.Exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
