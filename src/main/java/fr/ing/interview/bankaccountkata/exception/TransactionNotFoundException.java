package fr.ing.interview.bankaccountkata.exception;

public class TransactionNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public TransactionNotFoundException(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public TransactionNotFoundException() {
        super("could not find vocabulary");
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }
}