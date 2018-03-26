package com.example.demo.exceptions;
/***
 * Exception throws when Customer not exist
 * @author Elad Cohen
 *
 */
public class CustomerDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerDoesNotExistException() {
		super();
	}

	public CustomerDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerDoesNotExistException(String message) {
		super(message);
	}

	public CustomerDoesNotExistException(Throwable cause) {
		super(cause);
	}

	
}
