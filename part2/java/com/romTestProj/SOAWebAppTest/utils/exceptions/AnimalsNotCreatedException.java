package com.romTestProj.SOAWebAppTest.utils.exceptions;

public class AnimalsNotCreatedException extends RuntimeException{
	private final int errorNumber = 1;
	
	public AnimalsNotCreatedException(String message){
		super(message);
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}
}
