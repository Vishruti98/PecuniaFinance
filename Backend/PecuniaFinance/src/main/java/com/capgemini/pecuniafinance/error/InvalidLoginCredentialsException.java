package com.capgemini.pecuniafinance.error;

public class InvalidLoginCredentialsException extends Exception {


	public InvalidLoginCredentialsException() {
		super();
	}

	public InvalidLoginCredentialsException( String message) {
		super(message);

	}
}
