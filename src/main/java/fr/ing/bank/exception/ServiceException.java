package fr.ing.bank.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorCatalog errorCatalog;

    public ServiceException(ErrorCatalog errorCatalog, String message) {
        super(message);
        this.errorCatalog = errorCatalog;
    }

    public ServiceException(ErrorCatalog errorCatalog) {
        this(errorCatalog, errorCatalog.getMessage());
    }
}
