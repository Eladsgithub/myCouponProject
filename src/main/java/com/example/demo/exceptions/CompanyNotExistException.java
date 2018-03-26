package com.example.demo.exceptions;
/***
 * Exception throws when Company not exist
 * @author Elad Cohen
 *
 */
public class CompanyNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotExistException() {
		super();
	}

	public CompanyNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompanyNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyNotExistException(String message) {
		super(message);
	}

	public CompanyNotExistException(Throwable cause) {
		super(cause);
	}

	

	
}