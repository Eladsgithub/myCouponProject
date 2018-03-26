package com.example.demo.exceptions;
/***
 * throws when Coupon already exist
 * @author Elad Cohen
 *
 */
public class CouponAllreadyExistsException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponAllreadyExistsException() {
		super();
	}

	public CouponAllreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponAllreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponAllreadyExistsException(String message) {
		super(message);
	}

	public CouponAllreadyExistsException(Throwable cause) {
		super(cause);
	}
		
		
}
