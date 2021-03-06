package com.capgemini.pecuniafinance.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class AccountIdNotFoundException extends RuntimeException{
	public AccountIdNotFoundException(String errorMsg){
		super(errorMsg);
	}

}
