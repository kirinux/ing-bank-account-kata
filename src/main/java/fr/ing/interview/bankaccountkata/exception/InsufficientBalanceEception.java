package fr.ing.interview.bankaccountkata.exception;

public class InsufficientBalanceEception extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceEception(Long id) {
        super("could not find vocabulary for the id : " + id);
    }

    public InsufficientBalanceEception(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public InsufficientBalanceEception() {
        super("could not find vocabulary");
    }

    public InsufficientBalanceEception(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalanceEception(Throwable cause) {
        super(cause);
    }
}