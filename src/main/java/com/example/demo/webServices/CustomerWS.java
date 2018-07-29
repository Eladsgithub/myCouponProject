package com.example.demo.webServices;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DBDAO.CouponRepo;
import com.example.demo.Entity.Coupon;
import com.example.demo.Facades.CustomerFacade;
import com.example.demo.common.ClientType;
import com.example.demo.common.CouponType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulAmountException;
import com.example.demo.exceptions.CouponPurchaseunsuccesfulEndDateExceededException;
import com.example.demo.exceptions.CustomerDoesNotExistException;

@CrossOrigin("*")
@RestController
public class CustomerWS {

	@Autowired CustomerFacade cuf;
//	@Autowired Customer custLoggedInId;
	@Autowired CouponRepo couponRepo;
	@Autowired CouponSystem cs;
	
	private CustomerFacade getFacade(HttpServletRequest req)
	{
		return (CustomerFacade) cs.login("moshe", "qqqq", ClientType.CUSTOMER);
	}
	
	@RequestMapping(value = "/CustomerWS/purchaseCoupon/{c}", method = RequestMethod.GET)
	public void purchaseCoupon(HttpServletRequest req,@PathVariable ("c")String c) throws CouponDoesNotExistsException, 
	CouponPurchaseunsuccesfulAmountException, CouponPurchaseunsuccesfulEndDateExceededException, 
	InterruptedException, CustomerDoesNotExistException
	{
		Coupon coup = couponRepo.findByTitle(c);
		getFacade(req).purchaseCoupon(coup);
//		cuf.purchaseCoupon(pur, custLoggedInId);
	}
	@RequestMapping(value = "/CustomerWS/getAllPurchasedCoupons", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllPurchasedCoupons(HttpServletRequest req) throws InterruptedException
	{
		CustomerFacade cuf = getFacade(req);
		return cuf.getAllPurchasedCoupons();
		
	}
	
	@RequestMapping(value = "/CustomerWS/getAllPurchasedCouponsByType/{type}", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllPurchasedCouponsByType(HttpServletRequest req,@PathVariable("type") CouponType type) throws InterruptedException 
	{
		CustomerFacade cuf = getFacade(req);
		return cuf.getAllPurchasedCouponsByType(type);
	}
	
	@RequestMapping(value = "/CustomerWS/getAllPurchasedCouponsByPrice/{price}", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllPurchasedCouponsByPrice(HttpServletRequest req, @PathVariable("price") double price) throws InterruptedException 
	{
		return getFacade(req).getAllPurchasedCouponsByPrice(price);
	}
	
	
	
	
	
	
	
	
	
}
