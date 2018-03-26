package com.example.demo.exceptions;
/***
 * Exception throws when Company already exist
 * @author Elad Cohen
 *
 */
public class CompanyAllreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyAllreadyExistsException() {
		super();
	}

	public CompanyAllreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompanyAllreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyAllreadyExistsException(String message) {
		super(message);
	}

	public CompanyAllreadyExistsException(Throwable cause) {
		super(cause);
	}

	

	
}
