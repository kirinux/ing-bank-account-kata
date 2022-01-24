package fr.ing.interview.bankaccountkata.exception;

import javax.persistence.EntityNotFoundException;

public class INGEntityNotFoundException extends RuntimeException {
    public INGEntityNotFoundException(String message) {
        super(message);
    }
}
