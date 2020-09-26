package com.capgemini.pecuniafinance.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidValidationException extends RuntimeException{
	
	public InvalidValidationException() {
		super();
	}
	
	public InvalidValidationException(String s) {
		super(s);
	}
}
