package fr.ing.interview.bankaccountkata.exception;

public class ClientNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public ClientNotFoundException(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public ClientNotFoundException() {
        super("could not find vocabulary");
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }
}