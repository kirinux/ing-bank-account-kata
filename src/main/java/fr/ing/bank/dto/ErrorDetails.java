package fr.ing.bank.dto;

import lombok.Data;

@Data
public class ErrorDetails {

    private int code;

    private String message;

    public ErrorDetails(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
