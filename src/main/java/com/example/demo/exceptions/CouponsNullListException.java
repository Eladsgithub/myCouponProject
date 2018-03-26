package com.example.demo.exceptions;
/***
 * Throws when List equals null
 * @author Elad & Michal
 *
 */
public class CouponsNullListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponsNullListException() {
	}

	public CouponsNullListException(String message) {
		super(message);
	}

	public CouponsNullListException(Throwable cause) {
		super(cause);
	}

	public CouponsNullListException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponsNullListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
