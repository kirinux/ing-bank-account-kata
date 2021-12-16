package fr.ing.interview.bankaccountkata.exception;

public class AccountNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public AccountNotFoundException(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public AccountNotFoundException() {
        super("could not find vocabulary");
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFoundException(Throwable cause) {
        super(cause);
    }
}