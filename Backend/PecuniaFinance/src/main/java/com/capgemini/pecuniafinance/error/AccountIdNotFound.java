package com.capgemini.pecuniafinance.error;

@SuppressWarnings("serial")
public class AccountIdNotFound extends Exception{
	public AccountIdNotFound(String errorMsg){
		super(errorMsg);
	}

}
