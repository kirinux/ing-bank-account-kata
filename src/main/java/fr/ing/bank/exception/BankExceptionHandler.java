package fr.ing.bank.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import fr.ing.bank.dto.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDetails> handleServiceException(ServiceException exception) {
        ErrorCatalog errorCatalog = exception.getErrorCatalog();
        ErrorDetails errorDetails = new ErrorDetails(errorCatalog.getCode(), exception.getMessage());
        return ResponseEntity.status(getHttpStatus(errorCatalog)).body(errorDetails);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorDetails> handleJsonMappingException(JsonMappingException exception) {
        ErrorCatalog errorCatalog = ErrorCatalog.INVALID_ACCOUNT_TYPE;
        ErrorDetails errorDetails = new ErrorDetails(errorCatalog.getCode(), exception.getMessage());
        return ResponseEntity.status(getHttpStatus(errorCatalog)).body(errorDetails);
    }

    private HttpStatus getHttpStatus(ErrorCatalog errorCatalog) {
        switch (errorCatalog) {
            case ACCOUNT_NOT_FOUND:
            case CUSTOMER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case TRANSACTION_NOT_ALLOWED:
            case BAD_DATA_ARGUMENTS:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
