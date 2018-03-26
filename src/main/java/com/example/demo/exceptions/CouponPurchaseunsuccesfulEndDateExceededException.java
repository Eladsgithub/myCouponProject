package com.example.demo.exceptions;

/***
 * throws when Coupon purchase unsuccessful due to end date exceeded
 * @author Elad Cohen
 *
 */
public class CouponPurchaseunsuccesfulEndDateExceededException extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public CouponPurchaseunsuccesfulEndDateExceededException(String message) {
		super();
	}

}
