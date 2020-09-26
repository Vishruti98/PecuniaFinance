package com.capgemini.pecuniafinance.error;

@SuppressWarnings("serial")
public class InvalidValidationException extends RuntimeException{
	
	public InvalidValidationException() {
		super();
	}
	
	public InvalidValidationException(String s) {
		super(s);
	}
}
