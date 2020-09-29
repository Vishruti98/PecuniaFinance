package com.capgemini.pecuniafinance.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class InvalidLoginCredentialsException extends RuntimeException {


	public InvalidLoginCredentialsException() {
		super();
	}

	public InvalidLoginCredentialsException( String message) {
		super(message);

	}
}
