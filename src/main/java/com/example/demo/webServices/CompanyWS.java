package com.example.demo.webServices;

import java.util.ArrayList;
import java.util.Date;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DBDAO.CouponRepo;
import com.example.demo.Entity.Coupon;
import com.example.demo.Facades.CompanyFacade;
import com.example.demo.common.ClientType;
import com.example.demo.common.CouponType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CouponAllreadyExistsException;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponsNullListException;

@CrossOrigin("*")
@RestController
public class CompanyWS {
	
	@Autowired CouponSystem cs;
	@Autowired CouponRepo cr;
	

	private CompanyFacade getFacade(HttpServletRequest req)
	{
		
		return (CompanyFacade) cs.login("TEVA", "1234", ClientType.COMPANY);
	}
		
	@RequestMapping(value = "/CompanyWS/createCoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCoupon (HttpServletRequest req,@RequestBody Coupon c) throws CompanyAllreadyExistsException, InterruptedException, CouponAllreadyExistsException, CompanyNotExistException 
	{
			getFacade(req).createCoupon(c);
	}
	@RequestMapping (value = "/CompanyWS/removeCoupon/{c}", method = RequestMethod.DELETE)
	public void removeCoupon (HttpServletRequest req,@PathVariable ("c")String c)
			throws CouponDoesNotExistsException, CompanyNotExistException, InterruptedException  
	{
//		System.out.println("test");
		CompanyFacade cf = getFacade(req);
		Coupon re;
		re = cr.findByTitle(c);
		cf.removeCoupon(re);
	}
	
	@RequestMapping (value = "/CompanyWS/updateCoupon", method = RequestMethod.PUT)
	public void updateCoupon (HttpServletRequest req,@RequestBody Coupon c) throws CouponDoesNotExistsException, CompanyNotExistException, InterruptedException  
	{
		CompanyFacade cf = getFacade(req);
		cf.updateCoupon(c);
	}
	@RequestMapping (value = "/CompanyWS/getCoupon/{id}", method = RequestMethod.GET)
	public Coupon getCoupon (HttpServletRequest req, @PathVariable("id") long id) throws CouponDoesNotExistsException, InterruptedException
	{
		CompanyFacade cf = getFacade(req);
		return cf.getCoupon(id);	
		}
	
	@RequestMapping (value = "/CompanyWS/getAllCoupons", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCoupons(HttpServletRequest req) throws CouponsNullListException, InterruptedException
	{
		CompanyFacade cf = getFacade(req);
		return cf.getAllCoupons();
		}
	
	@RequestMapping (value = "/CompanyWS/getCouponsByType/{couponType}", method = RequestMethod.GET)
	public ArrayList<Coupon> getCouponsByType(HttpServletRequest req,@PathVariable ("couponType") CouponType couponType) throws CouponsNullListException, InterruptedException 
	{
		CompanyFacade cf = getFacade(req);
		return (ArrayList<Coupon>) cf.getCouponsByType(couponType);
		}
	
	@RequestMapping (value = "/CompanyWS/getCouponsByPrice/{price}", method = RequestMethod.GET)
	public ArrayList<Coupon> getCouponsByPrice(HttpServletRequest req,@PathVariable ("price") double price) throws InterruptedException
	{
		CompanyFacade cf = getFacade(req);
		return (ArrayList<Coupon>) cf.getCouponsByPrice(price);
		//, compLoggedInId
		}
	
	@RequestMapping (value = "/CompanyWS/getCouponsByDate/{endDate}", method = RequestMethod.GET)
	public ArrayList<Coupon> getCouponsByDate(HttpServletRequest req,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  @PathVariable ("endDate") Date endDate) throws InterruptedException
	{
		CompanyFacade cf = getFacade(req);
		return (ArrayList<Coupon>) cf.getCouponsByDate(endDate);
		}
	
	
	
}