package com.kata.error;

public class IlegalTransException extends RuntimeException {
	public enum ErrorAmountType{
		NEGATIVE_AMOUNT,
		ILEGAL_AMOUNT,
		OUT_OF_RANGE_AMOUNT;	
	}
	 
	private ErrorAmountType type;
    public IlegalTransException(String msg, ErrorAmountType type) {
        super(msg);
        this.type = type;
    }
    
    public ErrorAmountType getType() {
    	return type;
    }
}
