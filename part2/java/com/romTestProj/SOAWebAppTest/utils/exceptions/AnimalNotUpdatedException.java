package com.romTestProj.SOAWebAppTest.utils.exceptions;

public class AnimalNotUpdatedException extends RuntimeException{
	private final int errorNumber = 2;
	
	public AnimalNotUpdatedException(String message){
		super(message);
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}
}
