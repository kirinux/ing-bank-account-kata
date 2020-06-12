package fr.ing.interview.exception;

public class NonexistentAccountException extends BankAccountException {

    private static final String MESSAGE = "Account does not exist";

    public NonexistentAccountException() {
        super(MESSAGE);
    }

    public NonexistentAccountException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
