package fr.ing.interview.exception;

public class OverdraftForbiddenException extends BankAccountException {

    private static final String MESSAGE = "Overdraft is forbidden";

    public OverdraftForbiddenException() {
        super(MESSAGE);
    }

    public OverdraftForbiddenException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
