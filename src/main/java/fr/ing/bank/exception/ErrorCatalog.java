package fr.ing.bank.exception;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    CUSTOMER_NOT_FOUND(1, "customer not found !"),
    ACCOUNT_NOT_FOUND(2, "account not found !"),
    BAD_DATA_ARGUMENTS(3, "bad data arguments !"),
    TRANSACTION_NOT_ALLOWED(4, "transaction not allowed !"),
    INVALID_ACCOUNT_TYPE(5, "invalid account type");

    private final int code;

    private final String message;

    ErrorCatalog(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
