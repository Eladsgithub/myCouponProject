package com.example.demo.Facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.DBDAO.CompanyDBDAO;
import com.example.demo.DBDAO.CompanyRepo;
import com.example.demo.DBDAO.CouponDBDAO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.common.ClientType;
import com.example.demo.common.CouponType;
import com.example.demo.entry.CouponSystem;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CouponAllreadyExistsException;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponsNullListException;


/**
 * 
 * @author Elad Cohen
 * Details all of the methods Company can use
 *
 */

@Component
@Scope("prototype")
public class CompanyFacade implements CouponClientFacade{

	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CompanyRepo companyRepo;
//	variables and methods made for fake login and create coupon
	//private Company compLoggedIn = new Company();
	private Company compLoggedIn;
	public Date startDate = new Date();
	public Date endDate = new Date();
	public long LoggedInId;
	
	
	public Company getCompLoggedIn() {
		return compLoggedIn;
	}
	

	/**
	 * company login if successful sends a facade
	 * @param compName
	 * @param password
	 * @param clinetType
	 * 
	 */
	@Override
	public CouponClientFacade login(String compName, String password, ClientType clinetType) {
		try {
			if(companyDBDAO.login(compName, password) == true){
				compLoggedIn = companyRepo.findByCompName(compName);
				 LoggedInId = compLoggedIn.getId();
					return this;
			}
			else System.out.println("login failed please try again");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * create coupon calls create coupon at couponDBDAO and assigning it with parent company
	 * @param coupon
	 * @param compLoggedInId
	 * @throws CouponAllreadyExistsException
	 * @throws CompanyNotExistException
	 * @throws InterruptedException
	 */
		public void createCoupon(Coupon coupon) throws CouponAllreadyExistsException, CompanyNotExistException, InterruptedException
		{
			compLoggedIn.addCoupon(coupon);
//			couponDBDAO.createCoupon(coupon);
			companyDBDAO.updateCompany(compLoggedIn);
		}
		/**
		 * removeCoupon calls removeCoupon at couponDBDAO 
		 * @param coupon
		 * @throws CouponDoesNotExistsException
		 * @throws CompanyNotExistException
		 * @throws InterruptedException
		 */
		public void removeCoupon(Coupon coupon) throws CouponDoesNotExistsException, CompanyNotExistException, InterruptedException 
		{
			couponDBDAO.removeCoupon(coupon);
		}
		/**
		 * updateCoupon calls update Coupon at couponDBDAO 
		 * @param coupon
		 * @throws CouponDoesNotExistsException
		 * @throws CompanyNotExistException
		 * @throws InterruptedException
		 */
		public void updateCoupon(Coupon coupon) throws CouponDoesNotExistsException, CompanyNotExistException, InterruptedException 
		{
			couponDBDAO.updateCoupon(coupon);
		}
		/**
		 * getCoupon calls getCoupon at couponDBDAO 
		 * @param id
		 * @throws CouponDoesNotExistsException
		 * @throws InterruptedException
		 * @return coupon
		 */
		 public Coupon getCoupon(long id) throws CouponDoesNotExistsException, InterruptedException
		 {
			 return couponDBDAO.getCoupon(id);
			   
		 }
		 /**
			 * getAllCoupons calls getAllCoupons at couponDBDAO 
			 * @throws CouponsNullListException
			 * @throws InterruptedException
			 * @return list of coupons
			 */
		 public ArrayList<Coupon> getAllCoupons() throws CouponsNullListException, InterruptedException
		 {
			 return companyDBDAO.getAllCoupons(this.LoggedInId);
			// return couponDBDAO.getAllCoupons();
		 }
		 /**
			 * getCouponsByType calls getCouponsByType at couponDBDAO 
			 * @param couponType
			 * @throws CouponsNullListException
			 * @throws InterruptedException
			 * @return list of coupons by type
			 */
		 public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponsNullListException, InterruptedException{
			 return companyDBDAO.getAllCouponsByType(couponType, this.LoggedInId);
			 //return couponDBDAO.getCouponByType(couponType,this.LoggedInId);
		 }
		 /**
			 * getCouponsByPrice calls getCouponsByType at couponDBDAO 
			 * @param price
			 * @throws CouponsNullListException
			 * @throws InterruptedException
			 * @return list of coupons by price
			 */
		 public Collection<Coupon> getCouponsByPrice(double price) throws InterruptedException{
			 return companyDBDAO.getAllCouponsByPrice(price, this.LoggedInId);
			 //return couponDBDAO.getCouponByPrice(price);
		 }
		 /**
			 * getCouponsByPrice calls getCouponsByType at couponDBDAO 
			 * @param endDate
			 * @throws InterruptedException
			 * @return list of coupons by price
			 */
		 public ArrayList<Coupon> getCouponsByDate(Date endDate) throws InterruptedException{
			 return companyDBDAO.getAllCouponsByDate(endDate, this.LoggedInId);
			// return couponDBDAO.getCouponByDate(endDate);
		 }
		 
		 
		 
}


