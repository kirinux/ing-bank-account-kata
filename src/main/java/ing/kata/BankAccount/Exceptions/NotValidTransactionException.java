package ing.kata.BankAccount.Exceptions;

public class NotValidTransactionException extends RuntimeException {

    public NotValidTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
