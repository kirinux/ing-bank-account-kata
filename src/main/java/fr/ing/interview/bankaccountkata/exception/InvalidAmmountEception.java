package fr.ing.interview.bankaccountkata.exception;

public class InvalidAmmountEception extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAmmountEception(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public InvalidAmmountEception(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public InvalidAmmountEception() {
        super("could not find vocabulary");
    }

    public InvalidAmmountEception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAmmountEception(Throwable cause) {
        super(cause);
    }
}