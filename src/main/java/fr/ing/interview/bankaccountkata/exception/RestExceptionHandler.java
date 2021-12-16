package fr.ing.interview.bankaccountkata.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	
	

	@ExceptionHandler(value = { ClientNotFoundException.class})
	public ResponseEntity<Object> clientNotFoundException(ClientNotFoundException e, WebRequest request) {
		logger.error(e.getMessage());
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}
	
	
	@ExceptionHandler(value = { AccountNotFoundException.class })
	public ResponseEntity<Object> accountNotFoundException(AccountNotFoundException e, WebRequest request) {
	
		logger.error(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}
	
	@ExceptionHandler(value = { TransactionNotFoundException.class })
	public ResponseEntity<Object> transactionNotFoundException(TransactionNotFoundException e, WebRequest request) {
	
		logger.error(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}
	
	@ExceptionHandler(value = { InvalidAmmountEception.class })
	public ResponseEntity<Object> invalidAmmountEception(InvalidAmmountEception e, WebRequest request) {
		logger.error(HttpStatusMessage.INVALID_AMMOUNT);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatusMessage.INVALID_AMMOUNT);

		
	}
	
	@ExceptionHandler(value = { InsufficientBalanceEception.class })
	public ResponseEntity<Object> insufficientBalanceEception(InsufficientBalanceEception e, WebRequest request) {
		logger.error(HttpStatusMessage.INSUFFICIENT_BALANCE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatusMessage.INSUFFICIENT_BALANCE);

		
	}
	
	@ExceptionHandler(value = { InvalidTransactionTypeEception.class })
	public ResponseEntity<Object> invalidTransactionTypeEception(InvalidTransactionTypeEception e, WebRequest request) {
		logger.error(HttpStatusMessage.INVALID_TRANSACTION_TYPE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatusMessage.INVALID_TRANSACTION_TYPE);

		
	}
	

}
