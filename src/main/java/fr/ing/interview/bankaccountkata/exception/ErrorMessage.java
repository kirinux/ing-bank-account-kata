package fr.ing.interview.bankaccountkata.exception;

public class ErrorMessage {

	private int statusCode;
	private String message;
	
	
	
	public ErrorMessage() {
		super();
	}
	public ErrorMessage( String message,int statusCode) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "status code : " + Integer.toString(getStatusCode()) + "\n"
		+ "message: " + getMessage();
	}

}
