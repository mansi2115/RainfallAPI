package com.training.rainfall.exception;

public class InvalidInputException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidInputException() {
		super();
	
	}

	/**
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
	
	}
	
	

}
