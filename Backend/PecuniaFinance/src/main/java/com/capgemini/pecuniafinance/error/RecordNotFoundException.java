package com.capgemini.pecuniafinance.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException(String s) {
		super(s);
	}
}
