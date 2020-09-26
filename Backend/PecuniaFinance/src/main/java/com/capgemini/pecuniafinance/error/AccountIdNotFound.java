package com.capgemini.pecuniafinance.exceptions;

@SuppressWarnings("serial")
public class AccountIdNotFound extends Exception{
	public AccountIdNotFound(String errorMsg){
		super(errorMsg);
	}

}
