package fr.ing.interview.bankaccountkata.exception;

public class InvalidTransactionTypeEception extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTransactionTypeEception(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public InvalidTransactionTypeEception(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public InvalidTransactionTypeEception() {
        super("could not find vocabulary");
    }

    public InvalidTransactionTypeEception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTransactionTypeEception(Throwable cause) {
        super(cause);
    }
}