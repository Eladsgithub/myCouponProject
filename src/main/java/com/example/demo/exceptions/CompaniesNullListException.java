package com.example.demo.exceptions;
/***
 * Throws when List equals null
 * @author Elad Cohen
 *
 */
public class CompaniesNullListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompaniesNullListException() {
	}

	public CompaniesNullListException(String message) {
		super(message);
	}

	public CompaniesNullListException(Throwable cause) {
		super(cause);
	}

	public CompaniesNullListException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompaniesNullListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
