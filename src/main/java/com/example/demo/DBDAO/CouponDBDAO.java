package com.example.demo.DBDAO;

import java.util.ArrayList;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CouponDAO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.MyLogger;
import com.example.demo.common.CouponType;
import com.example.demo.connection.ConnectionPool;
import com.example.demo.connection.DBConnection;
import com.example.demo.exceptions.CompanyAllreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CouponAllreadyExistsException;
import com.example.demo.exceptions.CouponDoesNotExistsException;
import com.example.demo.exceptions.CouponsNullListException;

/***
*
* @author Elad Cohen
* CouponDBDAO implements the methods from Interface CouponDAO
*/
@Service
public class CouponDBDAO implements CouponDAO{
	
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	MyLoggerDBDAO myLoggerDBDAO;
	@Autowired
	MyLoggerRepo myLoggerRepo;
	
	
	//instance of Logger to with date inside to log action message 
	MyLogger logger = new MyLogger(new Date(), "action");
	
	/***
	 * Creating Coupon and assigning it to a company
	 * 
	 * @param coupon
	 * @param company
	 * @throws CompanyNotExistException
	 * @throws CouponExistException
	 */
	@Override
	public void createCoupon(Coupon coupon) 
			throws CouponAllreadyExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Coupon coupFromDB = couponRepo.findByTitle(coupon.getTitle());
//		coupon.setCompany(compLoggedInId);
		if (coupFromDB == null){
			couponRepo.save(coupon);
			logger.setAction("createCoupon succeeded");
			myLoggerDBDAO.logAction(logger);
			ConnectionPool.getInstance().returnConenction(dbConnection);
		}
		else {
			ConnectionPool.getInstance().returnConenction(dbConnection);
			logger.setAction("create Coupon failed");
			myLoggerDBDAO.logAction(logger);
			throw new CouponAllreadyExistsException("coupon Already exists");

		}
	}
	/***
	 * Removing Coupon 
	 * 
	 * @param coupon
	 * @throws InterruptedException
	 * @throws CouponExistException
	 */
	@Override
	public void removeCoupon(Coupon coupon) 
			throws CouponDoesNotExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Coupon coupFromDB = couponRepo.findByTitle(coupon.getTitle());
		if (coupFromDB != null){
			couponRepo.removeCoupon(coupFromDB.getId());
			//, coupon.getCompany().getId()
			ConnectionPool.getInstance().returnConenction(dbConnection);
			logger.setAction("removeCoupon succeeded" + coupon.getId());
			myLoggerDBDAO.logAction(logger);
		}
		else
		{
			ConnectionPool.getInstance().returnConenction(dbConnection);
			logger.setAction("removeCoupon failed" + coupon.getId());
			myLoggerDBDAO.logAction(logger);
			throw new CouponDoesNotExistsException("coupon does not exist");
		}
	}
	/***
	 * Updating Coupon 
	 * 
	 * @param coupon
	 * @throws CouponDoesNotExistsException
	 * @throws InterruptedException
	 */

	@Override
	public void updateCoupon(Coupon coupon)
			throws CouponDoesNotExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Coupon coupFromDB = couponRepo.findByTitle(coupon.getTitle());
		if(coupFromDB!=null)
		{
		coupFromDB.setPrice(coupon.getPrice());
		coupFromDB.setEndDate(coupon.getEndDate());
		couponRepo.save(coupFromDB);
		logger.setAction("updateCoupon succeeded coupon named : " + coupon.getTitle() + "was updated ");
		myLoggerDBDAO.logAction(logger);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		
		}
		else 
			{
			logger.setAction("updateCoupon failed coupon id# " + coupon.getId());
			myLoggerDBDAO.logAction(logger);
			ConnectionPool.getInstance().returnConenction(dbConnection);
			throw new CouponDoesNotExistsException("coupon does not exist");
		}
	}
	/***
	 * getting a specific Coupon 
	 * 
	 * @param id
	 * @throws CouponDoesNotExistsException
	 * @throws InterruptedException
	 * @return coupon
	 */

	@Override
	public Coupon getCoupon(long id) throws CouponDoesNotExistsException, InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		Coupon coupFromDb = couponRepo.findById(id);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("getCoupon coupon id# " + id);
		myLoggerDBDAO.logAction(logger);
		return coupFromDb;
	}

	/***
	 * getting a list of Coupons 
	 * 
	 * @throws InterruptedException
	 * *@return coupon list
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> listOfAllCompanies  = (ArrayList<Coupon>) couponRepo.findAll();
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("get all Coupons");
		myLoggerDBDAO.logAction(logger);
		return listOfAllCompanies;
	}
	/***
	 * getting a list of Coupons by type 
	 * @param couponType
	 * @throws InterruptedException
	 * @return coupon list by type 
	 */
	@Override
	public ArrayList<Coupon> getCouponByType(CouponType couponType, long compLoggedInId) throws  InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> couponListByType = couponRepo.findByType(couponType);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("get all Coupons by type " + couponType);
		myLoggerDBDAO.logAction(logger);
//		return null;
		return couponListByType;
	}
	/***
	 * getting a list of Coupons by date 
	 * @param couponType
	 * @throws InterruptedException
	 * @return coupon list by type 
	 */
	public ArrayList<Coupon> getCouponByDate(Date endDate) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> couponListByDate = (ArrayList<Coupon>) couponRepo.findWhereEndDateLowerThan(endDate);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("get all Coupons by date " + endDate);
		myLoggerDBDAO.logAction(logger);
		return couponListByDate;
	}
	/***
	 * getting a list of Coupons up to a specific price 
	 * @param price
	 * @param company logged in id 
	 * @throws InterruptedException
	 * @return coupon list by price 
	 */
	public ArrayList<Coupon> getCouponByPrice(double price) throws InterruptedException {
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> couponListByPrice = (ArrayList<Coupon>) couponRepo.findWherePriceLowerThan(price);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("get all Coupons by price " + price);
		myLoggerDBDAO.logAction(logger);
		return couponListByPrice;
	}
	/***
	 * getting a list of Coupons up to a specific date 
	 * @param today
	 * @throws InterruptedException
	 * @return coupon list by date 
	 */
	@Override
	public void deleteExpiredCoupons(Date today) throws InterruptedException {	
		DBConnection dbConnection = ConnectionPool.getInstance().getConnection();
		couponRepo.deleteExpiredCoupons(today);
		ConnectionPool.getInstance().returnConenction(dbConnection);
		logger.setAction("dailey thread - all expired deleted");
		myLoggerDBDAO.logAction(logger);
		
	}

	
}

