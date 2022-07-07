package com.romTestProj.SOAWebAppTest.utils;

public class AnimalErrorResponse {
	private String errorMessage;
	
	public AnimalErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
