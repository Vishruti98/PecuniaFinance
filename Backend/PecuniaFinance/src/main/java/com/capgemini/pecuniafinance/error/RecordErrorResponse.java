package com.capgemini.pecuniafinance.error;

@SuppressWarnings("serial")
public class RecordErrorResponse extends Exception {
	private final int errorCode;
    private final String errorMessage;
   
    

	public int getErrorCode() {
		return errorCode;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public RecordErrorResponse(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
	
