package com.example.demo.exceptions;
/***
 * throws when Coupon not exist
 * @author Elad Cohen
 *
 */
public class CouponDoesNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponDoesNotExistsException() {
		super();
	}

	public CouponDoesNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponDoesNotExistsException(String message) {
		super(message);
	}

	public CouponDoesNotExistsException(Throwable cause) {
		super(cause);
	}
	
	
}
