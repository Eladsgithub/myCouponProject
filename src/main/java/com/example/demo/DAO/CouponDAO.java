package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.common.CouponType;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CouponAllreadyExistsException;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponsNullListException;
@Service
public interface CouponDAO {
		
	
	/***
	 * Creating new Coupon
	 * @param coupon
	 * @throws CouponAllreadyExistsException
	 * @throws InterruptedException 
	 */
	void createCoupon(Coupon coupon) throws CouponAllreadyExistsException , CompanyNotExistException, InterruptedException;
	
	/***
	 * Removing Coupon by id
	 * @param couponId
	 * @throws CouponDoesNotExistsException
	 * @throws InterruptedException 
	 */
	void removeCoupon(Coupon coupon)throws CouponDoesNotExistsException ,CompanyNotExistException, InterruptedException;
	/***
	 * Updating coupon
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @param companyId
	 * @throws CouponDoesNotExistsException
	 * @throws CompanyNotExistException
	 * @throws InterruptedException 
	 */
	void updateCoupon(Coupon coupon)throws CouponDoesNotExistsException , CompanyNotExistException, InterruptedException;
	/***
	 * Get All Coupons on DB
	 * @return ArrayList<Coupon>
	 * @throws InterruptedException 
	 */
	ArrayList<Coupon> getAllCoupons()throws CouponsNullListException, InterruptedException;
	
	/**
	 * Get all Coupons on DB by type
	 * @param couponType
	 * @return
	 * @throws InterruptedException 
	 */
	ArrayList<Coupon> getCouponByType(CouponType couponType, long compLoggedInId)throws CouponsNullListException, InterruptedException;
	
	/***
	 * Get any Coupon on DB
	 * @param id
	 * @return Coupon
	 * @throws InterruptedException 
	 */
	Coupon getCoupon(long id)throws CouponDoesNotExistsException, InterruptedException;

	void deleteExpiredCoupons(Date today) throws InterruptedException;



	
	

	

	
	
}
