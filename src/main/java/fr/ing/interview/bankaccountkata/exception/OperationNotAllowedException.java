package fr.ing.interview.bankaccountkata.exception;

public class OperationNotAllowedException extends RuntimeException{
    public OperationNotAllowedException() {
        super();
    }

    public OperationNotAllowedException(String s) {
        super(s);
    }
}
