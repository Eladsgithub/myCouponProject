package com.example.demo.exceptions;

/***
 * throws when Coupon purchase unsuccessful - 
 * customer already purchased this coupon - can only hold one of a kind
 * @author Elad Cohen
 *
 */
public class CouponPurchaseunsuccesfulOneOfAKindException extends Exception{
	
	private static final long serialVersionUID = 1L;

		public CouponPurchaseunsuccesfulOneOfAKindException(String message) {
			super();
		}
	}


