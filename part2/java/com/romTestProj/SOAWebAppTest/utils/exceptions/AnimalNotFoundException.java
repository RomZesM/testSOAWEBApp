package com.romTestProj.SOAWebAppTest.utils.exceptions;

public class AnimalNotFoundException extends RuntimeException{
	
	private final int errorNumber = 2;
	
	public AnimalNotFoundException(String message){
		super(message);
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}

}
