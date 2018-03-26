package com.example.demo.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DBDAO.CouponDBDAO;
/**
 * 
 * @author Elad Cohen
 *defines the daily thread that erases expired coupons from database
 */
public class DailyThread implements Runnable {

	
	@Autowired
	CouponDBDAO couponDBDAO;
	
	/**
	 * delete expired coupons from DB and sleep for 24 hours
	 */
	@Override
	public void run() {
		while (true) {
			try {
				couponDBDAO.deleteExpiredCoupons(new Date());
				Thread.sleep(86400000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
