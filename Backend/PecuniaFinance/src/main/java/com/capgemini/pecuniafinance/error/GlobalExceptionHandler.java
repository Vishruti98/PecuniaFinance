package com.capgemini.pecuniafinance.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RecordErrorResponse> handlerGenericException(Exception ex) {
		RecordErrorResponse errorResponse = new RecordErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"There is some technical issue!");
        return new ResponseEntity<RecordErrorResponse>(errorResponse, HttpStatus.OK);
	}
}