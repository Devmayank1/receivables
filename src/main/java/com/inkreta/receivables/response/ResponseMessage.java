package com.inkreta.receivables.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ResponseMessage {

	private ApiError errors;

	private boolean hasError = false;
	private Object results;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date timestamp = new Date();

	public ResponseMessage(ApiError errors) {
		super();
		this.setHasError(true);
		this.setErrors(errors);
		this.setResults(results);
	}

	public ResponseMessage(Object results) {
		super();
		this.setResults(results);
	}
	

	

	public ApiError getErrors() {
		return errors;
	}

	public Object getResults() {
		return results;
	}

	public Date getTimestamp() {
		return new Date(timestamp.getTime());
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setErrors(ApiError errors) {
		this.errors = errors;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public void setResults(Object results) {
		this.results = results;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = new Date(timestamp.getTime());
	}

}
