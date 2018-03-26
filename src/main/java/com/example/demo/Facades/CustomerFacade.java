package com.example.demo.Facades;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DBDAO.CustomerDBDAO;
import com.example.demo.DBDAO.CustomerRepo;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Customer;
import com.example.demo.common.ClientType;
import com.example.demo.common.CouponType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulAmountException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulEndDateExceededException;
/**
 * 
 * @author Elad Cohen
 * Details all of the methods Customer can use
 *
 */
@Component
public class CustomerFacade implements CouponClientFacade{

	@Autowired
	CustomerDBDAO customerDBDAO;
	@Autowired
	CouponSystem couponSystem;
	//member to hold the logged in customer for future use 
	Customer custLoggedInId = new Customer("Avi", "9012");
	
	/**
	 * Customer login if successful sends a facade
	 * @param name
	 * @param password
	 * @param clinetType
	 * 
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clinetType)
	{
		try {
			if(customerDBDAO.login(name, password) == true){
			custLoggedInId = new Customer(name, password);
			return this;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
		
	}
		/**
		 * purchase coupon call on customerDBDAO to purchase coupon and assigning it to a customer
		 * @param coupon
		 * @param custLoggedInId
		 * @throws CouponDoesNotExistsException
		 * @throws CouponPurchaseunsuccesfulAmountException
		 * @throws CouponPurchaseunsuccesfulEndDateExceededException
		 * @throws InterruptedException
		 */
		public void purchaseCoupon(Coupon coupon, Customer custLoggedInId) throws CouponDoesNotExistsException, CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, InterruptedException
		{
		customerDBDAO.purchaseCoupon(coupon, custLoggedInId);
		}
		/**
		 * getAllPurchasedCoupons call on customerDBDAO to getAllPurchasedCoupons  
		 * @return list of coupons
		 * @throws InterruptedException
		 */
		public Collection<Coupon> getAllPurchasedCoupons() throws InterruptedException{
			return customerDBDAO.getAllPurchasedCoupons(custLoggedInId.getId());
			 
		}
		/**
		 * getAllPurchasedCouponsByType call on customerDBDAO to getAllPurchasedCouponsByType  
		 * @return list of coupons by type
		 * @throws InterruptedException
		 */
		public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) throws InterruptedException{
			return customerDBDAO.getAllPurchasedCouponsByType(type, custLoggedInId.getId());
			 
		}
		/**
		 * getAllPurchasedCouponsByPrice call on customerDBDAO to getAllPurchasedCouponsByPrice  
		 * @return list of coupons by price
		 * @throws InterruptedException
		 */
		public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) throws InterruptedException{
			return customerDBDAO.getAllPurchasedCouponsByPrice(price, custLoggedInId.getId());
			
		}

		
}
