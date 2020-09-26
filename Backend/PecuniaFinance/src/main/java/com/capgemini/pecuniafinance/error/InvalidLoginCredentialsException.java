package com.capgemini.pecuniafinance.error;

@SuppressWarnings("serial")
public class InvalidLoginCredentialsException extends Exception {


	public InvalidLoginCredentialsException() {
		super();
	}

	public InvalidLoginCredentialsException( String message) {
		super(message);

	}
}
