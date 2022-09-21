package fr.ing.interview.bankaccountkata;

import fr.ing.interview.bankaccountkata.exception.*;
import fr.ing.interview.bankaccountkata.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalRestDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleClientCreationNotPossibleException(ClientCreationNotPossibleException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40001);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleClientNotFoundException(ClientNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40002);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleAccountCreationNotPossibleException(AccountCreationNotPossibleException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40003);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleAccountNotFoundException(AccountNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40004);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleTransactionNotPossibleException(TransactionNotPossibleException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40005);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleMoneyDepositNotPossibleException(MoneyDepositNotPossibleException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40006);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleMoneyMoneyWithdrawalNotPossibleException(MoneyWithdrawalNotPossibleException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(40007);
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleIRunTimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(500);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDescription("Une erreur technique s'est produite.");
        return errorResponse;
    }
}
