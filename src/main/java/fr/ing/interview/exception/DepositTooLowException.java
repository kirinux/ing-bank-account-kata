package fr.ing.interview.exception;

public class DepositTooLowException extends BankAccountException {

    private static final String MESSAGE = "Deposit is too low";

    public DepositTooLowException() {
        super(MESSAGE);
    }

    public DepositTooLowException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
