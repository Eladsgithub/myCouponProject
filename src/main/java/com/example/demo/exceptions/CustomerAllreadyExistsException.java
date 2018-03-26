package com.example.demo.exceptions;
/***
 * Throws When Customer already exist
 * @author Elad Cohen
 *
 */
public class CustomerAllreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAllreadyExistsException() {
		super();
	}

	public CustomerAllreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerAllreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerAllreadyExistsException(String message) {
		super(message);
	}

	public CustomerAllreadyExistsException(Throwable cause) {
		super(cause);
	}
	
	
}
