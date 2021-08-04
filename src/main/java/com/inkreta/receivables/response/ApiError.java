package com.inkreta.receivables.response;

public class ApiError {

    private String errorCode;
    private String errorMessage;
    private String errorTrace;
    
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	public String getErrorTrace() {
		return errorTrace;
	}
	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}
	public ApiError(String errorCode, String errorMessage, String trace) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTrace = trace;
	}
	public ApiError(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = message;
	}



}