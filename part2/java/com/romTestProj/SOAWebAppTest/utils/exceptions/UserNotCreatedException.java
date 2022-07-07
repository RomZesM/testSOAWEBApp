package com.romTestProj.SOAWebAppTest.utils.exceptions;

public class UserNotCreatedException extends RuntimeException{
	
	private final int errorNumber = 4;
	public UserNotCreatedException(String message){
		super(message);
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}
}
