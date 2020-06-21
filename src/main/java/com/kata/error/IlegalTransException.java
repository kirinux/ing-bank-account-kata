package com.kata.error;

public class IlegalTransException extends RuntimeException {
	
    public IlegalTransException(String msg) {
        super(msg);
    }
}
