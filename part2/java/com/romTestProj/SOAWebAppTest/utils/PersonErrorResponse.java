package com.romTestProj.SOAWebAppTest.utils;

public class PersonErrorResponse {
	private String errorMessage;
	
	public PersonErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
