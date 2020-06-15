package fr.ing.bank.dto;

import fr.ing.bank.exception.ErrorCatalog;
import lombok.Data;

@Data
public class ErrorDetails {

    private int code;

    private String message;

    public ErrorDetails(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDetails(ErrorCatalog errorCatalog) {
        this.code = errorCatalog.getCode();
        this.message = errorCatalog.getMessage();
    }
}
